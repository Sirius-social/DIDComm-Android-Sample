package com.sirius.travelpass.ui.auth.auth_third_identity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentAuthSecondBinding
import com.sirius.travelpass.databinding.FragmentAuthThirdBinding
import com.sirius.travelpass.databinding.FragmentAuthThirdIdentityBinding


class AuthThirdIdentityFragment : BaseFragment<FragmentAuthThirdIdentityBinding, AuthThirdIdentityViewModel>() {

    override fun setupViews() {
        super.setupViews()

        dataBinding.indicatorView.selectPage(1)

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_third_identity
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {


        model.goToTypeInfoScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToTypeInfoScreenLiveData.value = false
                baseActivity.pushPage(AuthThirdIChooseIdFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}