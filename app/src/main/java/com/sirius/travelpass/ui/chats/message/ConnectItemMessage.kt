package com.sirius.travelpass.ui.chats.message

import com.sirius.travelpass.R
import com.sirius.sdk.agent.listener.Event
import com.sirius.sdk_android.helpers.ScenarioHelper
import com.sirius.sdk_android.scenario.EventAction
import com.sirius.sdk_android.scenario.EventActionListener
import com.sirius.travelpass.base.App
import com.sirius.travelpass.repository.MessageRepository
import com.sirius.travelpass.repository.models.LocalMessage

class ConnectItemMessage : BaseItemMessage {

    constructor(event: Event?) : super(event)
    constructor(localMessage: LocalMessage?) : super(localMessage)

    override fun getType(): MessageType {
        if (isError) {
            return MessageType.Connected
        }
        return if (isAccepted) {
            MessageType.Connected
        } else {
            MessageType.Connect
        }
    }


    override fun accept(comment: String?) {
        ScenarioHelper.getInstance().acceptScenario("Invitee", message?.id ?: "", comment, object :
            EventActionListener {
            override fun onActionStart(action: EventAction, id: String, comment: String?) {
                startLoading(id)
            }

            override fun onActionEnd(
                action: EventAction,
                id: String,
                comment: String?,
                success: Boolean,
                error: String?
            ) {
                isError = !success
                isAccepted = success
                errorString = error
                commentString = error
                stopLoading(id)
                if(!isError){
                    messageRepository?.invitationSuccessLiveData?.postValue(id)
                }

            }

        })
    }

    override fun cancel() {
        ScenarioHelper.getInstance()
            .stopScenario("Invitee", message?.id ?: "", "Canceled By Me", object :
                EventActionListener {
                override fun onActionStart(action: EventAction, id: String, comment: String?) {
                    startLoading(id)
                }

                override fun onActionEnd(
                    action: EventAction,
                    id: String,
                    comment: String?,
                    success: Boolean,
                    error: String?
                ) {
                    isError = !success
                    isAccepted = success
                    errorString = error
                    commentString = error
                    stopLoading(id)
                    if (isAccepted) {
                        messageRepository?.invitationSuccessLiveData?.postValue(id)
                    } else if (isError) {
                        messageRepository?.invitationErrorLiveData?.postValue(
                            Pair(
                                isError,
                                errorString
                            )
                        )
                    }
                }
            })
    }

    override fun getText(): String {
        return "Sample did label"
    }

    override fun getTitle(): String {
        return "Connect?"
    }
}