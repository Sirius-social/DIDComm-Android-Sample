package com.sirius.travelpass.ui.qrcode

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentAuthThirdChooseBinding
import com.sirius.travelpass.databinding.FragmentMenuBinding
import com.sirius.travelpass.databinding.FragmentShowQrBinding


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
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}