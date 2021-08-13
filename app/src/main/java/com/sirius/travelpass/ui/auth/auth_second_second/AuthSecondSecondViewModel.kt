package com.sirius.travelpass.ui.auth.auth_second_second

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.R
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.repository.UserRepository

import javax.inject.Inject



open class AuthSecondSecondViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {


    val registerHintLiveData = MutableLiveData<String>("")
    val registerFieldHintLiveData = MutableLiveData<String>("")
    val registerBtnTextLiveData = MutableLiveData<String>("")
    val alreadyExistAccountTextLiveData = MutableLiveData<CharSequence>()
    val goToNextScreenLiveData = MutableLiveData<Boolean>()
    val changeNameScreenLiveData = MutableLiveData<Boolean>()
    val nextVisibilityLiveData = MutableLiveData<Int>()
    val countryCodeLiveData = MutableLiveData<String>("+1")
    val labelTextLiveData = MutableLiveData<String>("")



    fun onRegisterClick(v: View) {
        goToNextScreenLiveData.postValue(true)
    }

    fun onChangeNameClick(v: View){
        changeNameScreenLiveData.postValue(true)
    }

    override fun setupViews() {
        super.setupViews()
        isNextEnabled()
        labelTextLiveData.postValue(resourceProvider.getString(R.string.auth_second_title,userRepository.myUser.name ?: ""))
    }



    fun isNextEnabled()  {
        val isNextEnabled = !userRepository.myUser.pass.isNullOrEmpty() && !userRepository.myUser.uid.isNullOrEmpty()
        if(isNextEnabled){
            nextVisibilityLiveData.postValue( View.VISIBLE)
        }else{
            nextVisibilityLiveData.postValue(  View.INVISIBLE)
        }

    }

    fun setUserPassword(pass: String) {
        userRepository.myUser.pass = pass
    }

    fun setUserUid(uid: String) {
        userRepository.myUser.uid = uid
    }

    fun saveUser() {
        userRepository.saveUserToPref()
    }

}


