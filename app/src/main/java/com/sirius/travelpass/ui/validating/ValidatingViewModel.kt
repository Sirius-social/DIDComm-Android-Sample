package com.sirius.travelpass.ui.validating

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.repository.UserRepository

import javax.inject.Inject



open class ValidatingViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {


    val goToShowQrScreenLiveData = MutableLiveData<Boolean>()
    val cancelLiveData = MutableLiveData<Boolean>()
    val emptyVisibilityLiveData = MutableLiveData<Int>()
    val actionsListVisibilityLiveData = MutableLiveData<Int>()




    fun onConnectClick(v: View) {
        goToShowQrScreenLiveData.postValue(true)
    }

    fun onCancelClick(v: View) {
        cancelLiveData.postValue(true)
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


    override fun setupViews() {
        super.setupViews()
        showHideEmpty(true)
    }


}


