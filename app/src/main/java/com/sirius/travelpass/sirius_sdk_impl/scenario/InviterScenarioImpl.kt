package com.sirius.travelpass.sirius_sdk_impl.scenario

import com.sirius.sdk_android.scenario.EventAction
import com.sirius.sdk_android.scenario.impl.InviteeScenario
import com.sirius.sdk_android.scenario.impl.InviterScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository

class InviterScenarioImpl  constructor(val messageRepository: MessageRepository) : InviterScenario() {
    override fun onScenarioStart(id : String) {
       // messageRepository.invitationStartLiveData.postValue(true)
    }

    override fun onScenarioEnd(id : String,success: Boolean, error: String?) {
       // messageRepository.invitationStopLiveData.postValue(Pair(success, error))
    }

}