package com.sirius.travelpass.ui.menu

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentAuthSecondBinding
import com.sirius.travelpass.databinding.FragmentAuthThirdBinding
import com.sirius.travelpass.databinding.FragmentCredentialFaceBinding
import com.sirius.travelpass.ui.auth.auth_fourth.AuthFourthFragment
import com.sirius.travelpass.ui.auth.auth_third_identity.AuthThirdIdentityFragment


class CredentialFaceFragment : BaseFragment<FragmentCredentialFaceBinding, CredentialFaceViewModel>() {

    override fun setupViews() {
        super.setupViews()

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_credential_face
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToTypeInfoScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToTypeInfoScreenLiveData.value = false
                baseActivity.pushPage(AuthThirdIdentityFragment())
            }
        })
        model.goToNextInfoScreenLiveData.observe(this, Observer {
            if (it) {
                AppPref.getInstance().setShowFaceCredential(false)
                model.goToNextInfoScreenLiveData.value = false
                fragmentManager?.beginTransaction()?.remove(this)?.commit()
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}