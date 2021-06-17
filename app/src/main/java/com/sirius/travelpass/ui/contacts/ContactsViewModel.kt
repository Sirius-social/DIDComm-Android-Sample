package com.sirius.travelpass.ui.contacts

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.models.ui.ItemContacts
import com.sirius.travelpass.repository.UserRepository
import java.util.*

import javax.inject.Inject



open class ContactsViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {



    val adapterListLiveData : MutableLiveData<List<ItemContacts>> = MutableLiveData(listOf())
    val onChatClickLiveData : MutableLiveData<ItemContacts?> = MutableLiveData()
    val onDetailsClickLiveData : MutableLiveData<ItemContacts?> = MutableLiveData()


    fun onChatsClick(item: ItemContacts) {
        onChatClickLiveData.postValue(item)
    }

    fun onDetailsClick(item: ItemContacts) {
        onDetailsClickLiveData.postValue(item)
    }

    private fun createList() : MutableList<ItemContacts>{
        val list :  MutableList<ItemContacts> = mutableListOf()
        list.add(ItemContacts("Hoth departemnt", Date(),false))
        list.add(ItemContacts("Jedy academu", Date(),false))
        list.add(ItemContacts("USCIS", Date(1620761306000),true))
        list.add(ItemContacts("Airport milan", Date(1620761306000),true))
        return list

    }



    fun onFilterClick(v: View){

    }

    override fun setupViews() {
        super.setupViews()
        val list :  MutableList<ItemContacts> = createList()
        //TODO add date to list
        adapterListLiveData.postValue(list)
    }



}


