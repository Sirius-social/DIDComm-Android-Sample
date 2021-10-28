package com.sirius.travelpass.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.QuestionAnswerScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository

class QuestionAnswerScenarioImp  constructor(val messageRepository: MessageRepository,
                                                    val eventRepository: EventRepository) : QuestionAnswerScenario(eventRepository) {


}