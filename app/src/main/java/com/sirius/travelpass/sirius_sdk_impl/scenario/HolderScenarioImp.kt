package com.sirius.travelpass.sirius_sdk_impl.scenario

import com.sirius.sdk_android.scenario.EventAction
import com.sirius.sdk_android.scenario.impl.HolderScenario
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository
import com.sirius.travelpass.repository.models.LocalMessage


class HolderScenarioImp  constructor(val messageRepository: MessageRepository,
                                            val eventRepository: EventRepository) : HolderScenario(eventRepository) {




}