package com.sirius.travelpass.ui.qrcode

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseViewModel
import com.sirius.travelpass.repository.UserRepository
import com.sirius.travelpass.sirius_sdk_impl.SDKUseCase
import java.util.*


import javax.inject.Inject



open class ShowQrViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider,
    val sdkUseCase: SDKUseCase
) : BaseViewModel(resourcesProvider) {


    val goToScanQrScreenLiveData = MutableLiveData<Boolean>()
    val emptyVisibilityLiveData = MutableLiveData<Int>()
    val actionsListVisibilityLiveData = MutableLiveData<Int>()
    val qrCodeLiveData = MutableLiveData<String>()



    fun onScanQrClick(v: View) {
        goToScanQrScreenLiveData.postValue(true)
    }


    override fun setupViews() {
        super.setupViews()
        val inviteLink: String? =  sdkUseCase.generateInvitation()
        qrCodeLiveData.value = inviteLink ?: ""
    }




}


