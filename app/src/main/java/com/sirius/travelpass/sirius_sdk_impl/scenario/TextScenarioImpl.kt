package com.sirius.travelpass.sirius_sdk_impl.scenario

import com.sirius.sdk_android.scenario.impl.TextScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository

class TextScenarioImpl  constructor(val messageRepository: MessageRepository,
                                           val eventRepository: EventRepository): TextScenario(eventRepository) {

}