package com.sirius.travelpass.ui.chats

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.models.ui.ItemContacts
import com.sirius.travelpass.ui.chats.message.BaseItemMessage
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.sirius_sdk_impl.SDKUseCase
import com.sirius.travelpass.repository.UserRepository
import com.sirius.travelpass.transform.EventTransform
import java.util.*

import javax.inject.Inject


open class ChatsViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider,
    val eventRepository: EventRepository,
    val sdkUseCase: SDKUseCase
) : BaseViewModel(resourcesProvider) {


    val adapterListLiveData: MutableLiveData<List<BaseItemMessage>> = MutableLiveData(listOf())
    val enableSendIconLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val clearTextLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val eventStoreLiveData = eventRepository.eventStoreLiveData

    var item: ItemContacts? = null
    var messageText: String? = null


    fun enableSendIcon() {
        enableSendIconLiveData.postValue(!messageText.isNullOrEmpty())
    }

    private fun createList(): List<BaseItemMessage> {
        var list = eventRepository.loadAllEventsForPairwise(item?.id ?: "").map {
            EventTransform.eventToBaseItemMessage(it)
        }
        if (list.isEmpty()) {
            val event = EventTransform.itemContactsToEvent(item, eventRepository)
            val message = EventTransform.eventToBaseItemMessage(event)
            list = list.toMutableList()

            list.add(message)
        }

        Collections.sort(
            list,
            kotlin.Comparator { o1, o2 -> o1.date?.compareTo(o2.date ?: Date(0)) ?: -1 })
        return list
    }


    fun onChooseIdClick(v: View) {

    }

    fun onFilterClick(v: View) {

    }

    fun onSendClick(v: View) {
        val event = sdkUseCase.sendTextMessageForPairwise(item?.id ?: "", messageText)
        event?.let {
            eventRepository.storeEvent(event.message()?.id ?: "", event, "text")
            clearTextLiveData.postValue(true)
        }
    }


    fun updateList() {
        val list: List<BaseItemMessage> = createList()
        //TODO add date to list
        adapterListLiveData.postValue(list)
    }

    override fun setupViews() {
        super.setupViews()
        updateList()
        setTitle(item?.title)
        enableSendIcon()
    }


}


