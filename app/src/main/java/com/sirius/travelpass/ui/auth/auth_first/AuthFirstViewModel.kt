package com.sirius.travelpass.ui.auth.auth_first

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.repository.UserRepository

import javax.inject.Inject



open class AuthFirstViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {

    val registerHintLiveData = MutableLiveData<String>("")
    val registerFieldHintLiveData = MutableLiveData<String>("")
    val registerBtnTextLiveData = MutableLiveData<String>("")
    val alreadyExistAccountTextLiveData = MutableLiveData<CharSequence>()
    val goToNextScreenLiveData = MutableLiveData<Boolean>()
    val nextVisibilityLiveData = MutableLiveData<Int>()



    fun onNextClick(v: View) {
        goToNextScreenLiveData.postValue(true)
    }

    override fun setupViews() {
        super.setupViews()
        isNextEnabled()
    }


    fun isNextEnabled()  {
        val isNextEnabled = !userRepository.myUser.name.isNullOrEmpty()
        if(isNextEnabled){
            nextVisibilityLiveData.postValue( View.VISIBLE)
        }else{
            nextVisibilityLiveData.postValue(  View.INVISIBLE)
        }

    }

    fun setUserName(name: String) {
        userRepository.myUser.name = name
    }

    fun saveUser() {
        userRepository.saveUserToPref()
    }

    fun getUserName() : String?{
        return userRepository.myUser?.name
    }

}


