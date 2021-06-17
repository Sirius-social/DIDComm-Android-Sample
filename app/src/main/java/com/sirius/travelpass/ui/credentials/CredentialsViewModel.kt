package com.sirius.travelpass.ui.credentials

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.models.ui.ItemCredentials
import com.sirius.travelpass.repository.UserRepository
import java.util.*

import javax.inject.Inject



open class CredentialsViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {

    val adapterListLiveData : MutableLiveData<List<ItemCredentials>> = MutableLiveData(listOf())



    private fun createList() : MutableList<ItemCredentials>{
        val list :  MutableList<ItemCredentials> = mutableListOf()
        list.add(ItemCredentials("Hoth departemnt", Date(),false))
        list.add(ItemCredentials("Jedy academu", Date(),false))
        list.add(ItemCredentials("USCIS", Date(1620761306000),true))
        list.add(ItemCredentials("Airport milan", Date(1620761306000),true))
        return list

    }

    fun onFilterClick(v: View){

    }
    override fun setupViews() {
        super.setupViews()
       val list =  createList()
        adapterListLiveData.postValue(list)
    }

}


