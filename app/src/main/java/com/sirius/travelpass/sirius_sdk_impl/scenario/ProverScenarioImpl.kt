package com.sirius.travelpass.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.ProverScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository

class ProverScenarioImpl  constructor(val messageRepository: MessageRepository,
                                             val eventRepository: EventRepository): ProverScenario(eventRepository) {


}