package com.sirius.travelpass.sirius_sdk_impl

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sirius.travelpass.service.WebSocketService
import com.sirius.travelpass.utils.DateUtils.PATTERN_ROSTER_STATUS_RESPONSE2
import com.sirius.library.agent.BaseSender
import com.sirius.library.agent.aries_rfc.feature_0095_basic_message.Message

import com.sirius.library.agent.aries_rfc.feature_0113_question_answer.messages.QuestionMessage
import com.sirius.library.agent.aries_rfc.feature_0113_question_answer.messages.Recipes

import com.sirius.library.mobile.SiriusSDK
import com.sirius.library.mobile.helpers.ChanelHelper
import com.sirius.library.mobile.helpers.PairwiseHelper
import com.sirius.library.mobile.helpers.ScenarioHelper
import com.sirius.library.mobile.scenario.impl.InviterScenario
import com.sirius.library.mobile.utils.HashUtils


import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository
import com.sirius.travelpass.repository.models.LocalMessage
import com.sirius.travelpass.sirius_sdk_impl.scenario.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

import java.io.File
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SDKUseCase @Inject constructor(
    private val eventRepository: EventRepository,
    private val messageRepository: MessageRepository) {


    public fun startSocketService(context: Context) {
        val intent = Intent(context, WebSocketService::class.java)
        context.startService(intent)
    }

    private fun connectToSocket(context: Context, url: String) {
        val intent = Intent(context, WebSocketService::class.java)
        intent.setAction(WebSocketService.EXTRA_CONNECT)
        intent.putExtra("url", url)
        context.startService(intent)
    }


    private fun closeSocket(context: Context) {
        val intent = Intent(context, WebSocketService::class.java)
        intent.setAction(WebSocketService.EXTRA_CLOSE)
        context.startService(intent)
    }


    private fun sendMessToSocket(context: Context, endpoint: String, data: ByteArray) {
        val intent = Intent(context, WebSocketService::class.java)
        intent.setAction(WebSocketService.EXTRA_SEND)
        intent.putExtra("data", data)
        intent.putExtra("url", endpoint)
        context.startService(intent)
    }


    interface OnInitListener {
        fun initStart()
        fun initEnd()
    }

    fun initSdk(context: Context, userJid: String, pass: String, label : String,onInitListener: OnInitListener?) {
        onInitListener?.initStart()
        val mainDirPath = context.filesDir.absolutePath
        val walletDirPath = mainDirPath + File.separator + "wallet"
        val alias = HashUtils.generateHash(userJid)
        val passForWallet = HashUtils.generateHashWithoutStoredSalt(pass, alias)
        val projDir = File(walletDirPath)
        if (!projDir.exists()) {
            projDir.mkdirs()
        }
        val walletId = alias.substring(IntRange(0, 8))

        val sender = object : BaseSender() {
            override fun sendTo(endpoint: String?, data: ByteArray?): Boolean {
                if (endpoint?.startsWith("http")==true) {
                    Thread(Runnable {
                        //content-type
                        val ssiAgentWire: MediaType = "application/ssi-agent-wire".toMediaType()
                        var client: OkHttpClient = OkHttpClient()
                        Log.d("mylog200", "requset=" + String(data?: ByteArray(0)))
                        val body: RequestBody = RequestBody.create(ssiAgentWire, data?: ByteArray(0))
                        val request: Request = Request.Builder()
                            .url(endpoint?:"")
                            .post(body)
                            .build()
                        client.newCall(request).execute().use { response ->
                            Log.d("mylog200", "response=" + response.body?.string())
                            response.isSuccessful
                        }
                    }).start()
                } else if (endpoint?.startsWith("ws")==true) {
                    sendMessToSocket(context, endpoint, data?: ByteArray(0))
                }

                return false
            }

            override fun open(endpoint: String?) {
                connectToSocket(context, endpoint?:"")
            }





            override fun close() {
                closeSocket(context)
            }


        }
        val mediatorAddress = "wss://mediator.socialsirius.com/ws"
        val recipientKeys = "DjgWN49cXQ6M6JayBkRCwFsywNhomn8gdAXHJ4bb98im"
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                //  Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Thread(Runnable {
                SiriusSDK.getInstance().initialize(
                    mycontext = context, alias = walletId, pass = passForWallet,
                    mainDirPath = mainDirPath,
                    mediatorAddress = mediatorAddress,recipientKeys = listOf(recipientKeys),
                    label = label, baseSender = sender
                )
                ChanelHelper.getInstance().initListener()
                SiriusSDK.getInstance().connectToMediator(token)
                initScenario()
                onInitListener?.initEnd()
            }).start()
        })


    }


    private fun initScenario() {
        ScenarioHelper.getInstance().addScenario("Inviter",InviterScenarioImpl(messageRepository))
        ScenarioHelper.getInstance().addScenario("Invitee", InviteeScenarioImp(messageRepository,eventRepository))
        ScenarioHelper.getInstance().addScenario("Holder", HolderScenarioImp(messageRepository,eventRepository))
        ScenarioHelper.getInstance().addScenario("Text",TextScenarioImpl(messageRepository,eventRepository))
        ScenarioHelper.getInstance().addScenario("Prover", ProverScenarioImpl(messageRepository,eventRepository))
        ScenarioHelper.getInstance().addScenario("Question", QuestionAnswerScenarioImp(messageRepository,eventRepository))

    }


    fun sendTextMessageForPairwise(pairwiseDid: String, messageText: String?)  : LocalMessage {
        val pairwise = PairwiseHelper.getInstance().getPairwise(theirDid = pairwiseDid)
        val message = Message.builder().setContent(messageText).build()
        val localMessage = LocalMessage(pairwiseDid = pairwiseDid)
        localMessage.isMine = true
        localMessage.type = "text"
        localMessage.message = message.serialize()
        localMessage.sentTime = Date()
        pairwise?.let {
            SiriusSDK.getInstance().context.sendTo(message, pairwise)
        }
        return localMessage
    }


    fun generateInvitation() : String?{
        val inviter = ScenarioHelper.getInstance().getScenarioBy("Inviter") as? InviterScenario
        return inviter?.generateInvitation()
    }

    fun sendTestQuestion(pairwiseDid: String):LocalMessage{
        val pairwise = PairwiseHelper.getInstance().getPairwise(theirDid = pairwiseDid)
        val message = QuestionMessage.builder()
            .setQuestionText("Alice, are you on the phone with Bob from Faber Bank right now?")
            .setValidResponses(listOf("Yes, it's me","No, that's not me!"))
            .setQuestionDetail("This is optional fine-print giving context to the question and its various answers.").build()
        val localMessage = LocalMessage(pairwiseDid = pairwiseDid)
        localMessage.isMine = true
        localMessage.sentTime = Date()
        localMessage.type = "question"
        localMessage.message = message.serialize()
        Thread(Runnable {
            pairwise?.let {
                Recipes.askAndWaitAnswer(SiriusSDK.getInstance().context,message,pairwise)
            }
        }).start()
        return localMessage
    }
}