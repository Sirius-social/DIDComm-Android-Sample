package com.sirius.travelpass.ui.menu

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentAuthThirdChooseBinding
import com.sirius.travelpass.databinding.FragmentMenuBinding
import com.sirius.travelpass.ui.auth.auth_second.AuthSecondFragment
import com.sirius.travelpass.ui.qrcode.ShowQrFragment


class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override fun setupViews() {
        super.setupViews()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_menu
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToShowQrScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToShowQrScreenLiveData.value = false
                baseActivity.pushPage(ShowQrFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}