package com.sirius.travelpass.ui.chats.message


import android.util.Log
import com.sirius.travelpass.models.ui.ItemCredentialsDetails
import com.sirius.sdk.agent.aries_rfc.feature_0036_issue_credential.messages.ProposedAttrib
import com.sirius.sdk.agent.aries_rfc.feature_0113_question_answer.mesages.QuestionMessage
import com.sirius.sdk.agent.listener.Event
import com.sirius.sdk_android.helpers.ScenarioHelper
import com.sirius.travelpass.models.ui.ItemQuestionAnswer
import org.json.JSONObject
import java.util.*

class QuestionItemMessage(event: Event) : BaseItemMessage(event) {


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
    override fun setupFromEvent(event: Event?) {
        super.setupFromEvent(event)
        val questionMessage = event?.message() as? QuestionMessage
       // expiresTime = questionMessage?.expiresTime
        hint = questionMessage?.questionDetail
        name = questionMessage?.questionText
        answerList = questionMessage?.getValidResponses()?.map {
            ItemQuestionAnswer(it)
        }
    }


    override fun accept(comment : String?) {
        ScenarioHelper.getInstance().acceptScenario("Question", message?.id ?: "",comment)
    }

    override fun cancel() {
        ScenarioHelper.getInstance().stopScenario("Question", message?.id ?: "", "Canceled By Me")
    }

    override fun getText(): String {
        return "offer sample"
    }

    override fun getTitle(): String {
        return name ?: ""
    }
}