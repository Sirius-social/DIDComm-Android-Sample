package com.sirius.travelpass.ui.qrcode

import android.graphics.Bitmap
import androidx.lifecycle.Observer
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment
import com.sirius.travelpass.databinding.FragmentShowQrBinding
import java.util.*


class ShowQrFragment : BaseFragment<FragmentShowQrBinding, ShowQrViewModel>() {

    override fun setupViews() {
        super.setupViews()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_show_qr
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {

        model.goToScanQrScreenLiveData.observe(this, Observer {
            if(it){
                model.goToScanQrScreenLiveData.value = false
                baseActivity.pushPage(ScanQrFragment())
            }
        })

        model.qrCodeLiveData.observe(this, Observer {
            updateQrCode(it)
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


    private fun updateQrCode(qrCode: String) {
        val hintsMap: MutableMap<EncodeHintType, Any?> = EnumMap(EncodeHintType::class.java)
        hintsMap[EncodeHintType.CHARACTER_SET] = "utf-8"
        hintsMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M

        val mWidth = 256
        val mHeight = 256

        try {
            val bitMatrix = QRCodeWriter().encode(qrCode,
                BarcodeFormat.QR_CODE, mWidth, mHeight, hintsMap)
            val pixels = IntArray(mWidth * mHeight)
            for (i in 0 until mHeight) {
                for (j in 0 until mWidth) {
                    if (bitMatrix[j, i]) {
                        pixels[i * mWidth + j] = 0x00000000
                    } else {
                        pixels[i * mWidth + j] = -0x1
                    }
                }
            }
            val bitmap = Bitmap.createBitmap(pixels, 0, mWidth, mWidth, mHeight,
                Bitmap.Config.RGB_565
            )
            dataBinding.qrImage.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

}