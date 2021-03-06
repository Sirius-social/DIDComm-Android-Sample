package com.sirius.travelpass.ui.activities.loader



import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseActivityModel
import com.sirius.travelpass.sirius_sdk_impl.SDKUseCase
import com.sirius.travelpass.repository.UserRepository

import javax.inject.Inject

class LoaderActivityModel @Inject constructor(
    resourceProvider: ResourcesProvider,
    private val sdkUseCase: SDKUseCase,
    private val userRepository: UserRepository
) :
    BaseActivityModel(resourceProvider) {

    val initStartLiveData = MutableLiveData<Boolean>()
    val initEndLiveData = MutableLiveData<Boolean>()

        fun initSdk(context: Context){
            val login = userRepository.myUser.uid ?: ""
            val pass = userRepository.myUser.pass ?:""
            val label = userRepository.myUser.name ?:""
            sdkUseCase.initSdk(context,login,pass, label,object : SDKUseCase.OnInitListener{
                override fun initStart() {
                    initStartLiveData.postValue(true)
                }

                override fun initEnd() {
                    initEndLiveData.postValue(true)
                }

            })
        }


}