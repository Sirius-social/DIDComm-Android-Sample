package com.sirius.travelpass.sirius_sdk_impl.scenario

import com.sirius.sdk_android.scenario.EventAction
import com.sirius.sdk_android.scenario.impl.ProverScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository

class ProverScenarioImpl  constructor(val messageRepository: MessageRepository,
                                             val eventRepository: EventRepository): ProverScenario(eventRepository) {


}