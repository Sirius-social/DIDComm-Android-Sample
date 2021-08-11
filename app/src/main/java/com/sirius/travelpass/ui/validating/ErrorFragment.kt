package com.sirius.travelpass.ui.validating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment
import com.sirius.travelpass.databinding.FragmentErrorBinding
import com.sirius.travelpass.databinding.FragmentValidatingBinding
import com.sirius.travelpass.ui.qrcode.ShowQrFragment


class ErrorFragment  : BaseFragment<FragmentErrorBinding, ErrorViewModel>() {




    companion object  {

        fun newInstance(error: String?): ErrorFragment {
            val args = Bundle()
            args.putString("error",error)
            val fragment = ErrorFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun setupViews() {
        super.setupViews()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_error
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