package com.sirius.travelpass.ui.activities.main




import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseActivityModel
import com.sirius.travelpass.models.ui.ItemContacts
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository
import com.sirius.travelpass.repository.models.LocalMessage
import com.sirius.travelpass.transform.LocalMessageTransform
import javax.inject.Inject

class MainActivityModel @Inject constructor(
    resourceProvider: ResourcesProvider,
    val eventRepository: EventRepository,
    val messageRepository: MessageRepository

) : BaseActivityModel(resourceProvider) {

   val invitationStartLiveData = messageRepository.invitationStartLiveData
   val invitationErrorLiveData = messageRepository.invitationErrorLiveData
   val invitationSuccessLiveData = messageRepository.invitationSuccessLiveData
   val eventStoreLiveData = messageRepository.eventStoreLiveData
   val eventStartLiveData = messageRepository.eventStartLiveData
   val eventStopLiveData = messageRepository.eventStopLiveData


    override fun onViewCreated() {
        super.onViewCreated()
    }

    fun getMessage(id : String) :  ItemContacts{
        val localMessage = messageRepository.getItemBy(id)
        return LocalMessageTransform.toItemContacts(localMessage)
    }
}