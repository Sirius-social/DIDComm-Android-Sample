package com.sirius.travelpass.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.InviteeScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository



class InviteeScenarioImp  constructor(val messageRepository: MessageRepository,
                                      val eventRepository: EventRepository) : InviteeScenario(eventRepository) {

    override fun onScenarioStart(id: String) {

    }

    override fun onScenarioEnd(id: String,success: Boolean, error: String?) {
        messageRepository.invitationStartLiveData.postValue(id)
        //messageRepository.invitationStopLiveData.postValue(Pair(id, error))
    }

}