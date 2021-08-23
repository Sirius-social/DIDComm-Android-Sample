package com.sirius.travelpass.ui.credentials

import android.view.View
import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentCredentialsBinding
import com.sirius.travelpass.models.ui.ItemCredentials


class CredentialsFragment : BaseFragment<FragmentCredentialsBinding, CredentialsViewModel>() {

    val adapter  = CredentialsListAdapter()


    override fun setupViews() {
        super.setupViews()
        dataBinding.credentialsRecyclerView.adapter = adapter
        if(AppPref.getInstance().isShowFaceCredential()){
            dataBinding.faceCredentialLayout.visibility = View.GONE
        }else{
            dataBinding.faceCredentialLayout.visibility = View.VISIBLE
        }

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_credentials
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })
    }

    private fun updateAdapter(data: List<ItemCredentials>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }


    override fun setModel() {
        dataBinding.viewModel = model
    }


}