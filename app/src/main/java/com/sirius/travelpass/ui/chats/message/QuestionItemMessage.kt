package com.sirius.travelpass.ui.chats.message


import com.sirius.travelpass.models.ui.ItemCredentialsDetails
import com.sirius.sdk.agent.aries_rfc.feature_0036_issue_credential.messages.ProposedAttrib
import com.sirius.sdk.agent.aries_rfc.feature_0113_question_answer.mesages.QuestionMessage
import com.sirius.sdk.agent.listener.Event
import com.sirius.sdk_android.helpers.ScenarioHelper
import com.sirius.sdk_android.scenario.EventAction
import com.sirius.sdk_android.scenario.EventActionListener
import com.sirius.travelpass.models.ui.ItemQuestionAnswer
import com.sirius.travelpass.repository.models.LocalMessage
import org.json.JSONObject
import java.util.*

class QuestionItemMessage : BaseItemMessage {


    constructor(event: Event?) : super(event)
    constructor(localMessage: LocalMessage?) : super(localMessage)

    override fun getType(): MessageType {
        return if (isAccepted) {
            MessageType.QuestionAccepted
        } else {
            MessageType.Question
        }
    }

    var hint: String? = null
    var expiresTime: Date? = null
    var answerList: List<ItemQuestionAnswer>? = null
    var name: String? = null

    fun setupMessage(questionMessage : QuestionMessage?){

        // expiresTime = questionMessage?.expiresTime
        hint = questionMessage?.questionDetail
        name = questionMessage?.questionText
        answerList = questionMessage?.getValidResponses()?.map {
            ItemQuestionAnswer(it)
        }
    }
    override fun setupFromLocalMessage(localMessage: LocalMessage?) {
        super.setupFromLocalMessage(localMessage)
        val questionMessage = localMessage?.message() as? QuestionMessage
        setupMessage(questionMessage)
    }
    override fun setupFromEvent(event: Event?) {
        super.setupFromEvent(event)
        val questionMessage = event?.message() as? QuestionMessage
        setupMessage(questionMessage)
    }


    override fun accept(comment : String?) {
        ScenarioHelper.getInstance().acceptScenario("Question", message?.id ?: "",comment, object:
            EventActionListener{
            override fun onActionStart(action: EventAction, id: String, comment: String?) {

            }

            override fun onActionEnd(
                action: EventAction,
                id: String,
                comment: String?,
                success: Boolean,
                error: String?
            ) {

            }

        })
    }

    override fun cancel() {
        ScenarioHelper.getInstance().stopScenario("Question", message?.id ?: "", "Canceled By Me", object :
            EventActionListener {
            override fun onActionStart(action: EventAction, id: String, comment: String?) {

            }

            override fun onActionEnd(
                action: EventAction,
                id: String,
                comment: String?,
                success: Boolean,
                error: String?
            ) {

            }

        } )
    }

    override fun getText(): String {
        return "offer sample"
    }

    override fun getTitle(): String {
        return name ?: ""
    }
}