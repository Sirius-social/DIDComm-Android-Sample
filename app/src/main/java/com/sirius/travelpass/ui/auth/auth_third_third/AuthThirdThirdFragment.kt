package com.sirius.travelpass.ui.auth.auth_third_third

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentAuthSecondBinding
import com.sirius.travelpass.databinding.FragmentAuthThirdThirdBinding
import com.sirius.travelpass.ui.auth.auth_third.AuthThirdFragment


class AuthThirdThirdFragment : BaseFragment<FragmentAuthThirdThirdBinding, AuthThirdThirdViewModel>() {


    override fun setupViews() {
        super.setupViews()
        dataBinding.indicatorView.selectPage(3)

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_third_third
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToNextScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToNextScreenLiveData.value = false
                baseActivity.pushPage(AuthThirdFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}