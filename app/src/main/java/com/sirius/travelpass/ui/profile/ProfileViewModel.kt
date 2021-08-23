package com.sirius.travelpass.ui.profile

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.transform.EventTransform
import com.sirius.travelpass.models.ui.ItemActions
import com.sirius.travelpass.repository.EventRepository
import com.sirius.travelpass.repository.MessageRepository
import com.sirius.travelpass.repository.UserRepository

import javax.inject.Inject



open class ProfileViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider,
    val eventRepository: EventRepository,
    val messageRepository: MessageRepository
) : BaseViewModel(resourcesProvider) {


    val goToShowQrScreenLiveData = MutableLiveData<Boolean>()
    val emptyVisibilityLiveData = MutableLiveData<Int>()
    val actionsListVisibilityLiveData = MutableLiveData<Int>()
    val adapterListLiveData : MutableLiveData<List<ItemActions>> = MutableLiveData(listOf())
    val eventLiveData = messageRepository.eventStoreLiveData



    fun onConnectClick(v: View) {
        goToShowQrScreenLiveData.postValue(true)
    }


    fun showHideEmpty(show : Boolean){
        if(show){
            emptyVisibilityLiveData.postValue(View.VISIBLE)
            actionsListVisibilityLiveData.postValue(View.GONE)
        }else{
            emptyVisibilityLiveData.postValue(View.GONE)
            actionsListVisibilityLiveData.postValue(View.VISIBLE)
        }

    }


    private fun createList() : List<ItemActions>{
      /*  val   list = eventRepository.loadAllUnacceptedEventActions().map {
             EventTransform.eventToItemActions(it)
        }*/
        return listOf()

    }

    fun updateList(){
        val list =  createList()
        showHideEmpty(list.isEmpty())
        adapterListLiveData.postValue(list)
    }

    override fun setupViews() {
        super.setupViews()
        updateList()
    }


}


