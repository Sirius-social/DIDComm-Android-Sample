package com.sirius.travelpass.base.ui

import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.design.BottomNavView


abstract class BaseActivityModel(resourceProvider: ResourcesProvider) : BaseViewModel(
    resourceProvider
)  {

    val  bottomNavClick : MutableLiveData<BottomNavView.BottomTab> =  MutableLiveData(BottomNavView.BottomTab.Contacts)
    var selectedTab = MutableLiveData(BottomNavView.BottomTab.Contacts)
    val  isVisibleUnauthBottomBar   : MutableLiveData<Pair<Boolean,Boolean>> =  MutableLiveData<Pair<Boolean,Boolean>>(Pair(false,false))

    fun getOnBottomNavClickListner() : BottomNavView.OnbottomNavClickListener{
        return object : BottomNavView.OnbottomNavClickListener{
            override fun onBottomClick(tab: BottomNavView.BottomTab) {
                bottomNavClick.postValue(tab)
            }
        }
    }

}