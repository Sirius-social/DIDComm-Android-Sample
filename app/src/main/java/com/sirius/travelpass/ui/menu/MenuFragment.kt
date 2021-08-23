package com.sirius.travelpass.ui.menu

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseFragment

import com.sirius.travelpass.databinding.FragmentMenuBinding
import com.sirius.travelpass.models.ui.ItemActions
import com.sirius.travelpass.models.ui.ItemCredentials
import com.sirius.travelpass.transform.EventTransform
import com.sirius.travelpass.transform.LocalMessageTransform
import com.sirius.travelpass.ui.auth.auth_third_third.AuthThirdThirdFragment
import com.sirius.travelpass.ui.chats.ChatsFragment
import com.sirius.travelpass.ui.credentials.CredentialsListAdapter
import com.sirius.travelpass.ui.qrcode.ShowQrFragment
import com.sirius.travelpass.utils.extensions.observeOnce


class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {


    val adapter = ActionsAdapter()


    override fun setupViews() {
        super.setupViews()
        dataBinding.actionsRecycler.adapter = adapter
        adapter.setOnAdapterItemClick {
            val message = LocalMessageTransform.toLocalMessage(it, model.messageRepository)
                .observeOnce(model) {
                    val itemContact = LocalMessageTransform.toItemContacts(it)
                    baseActivity.pushPage(ChatsFragment.newInstance(itemContact))
                }
        }

        if (AppPref.getInstance().isShowFaceCredential()) {
            childFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, CredentialFaceFragment()).commit()
        }
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

        model.eventLiveData.observe(this, Observer {
            model.updateList()
        })

        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })
    }

    private fun updateAdapter(data: List<ItemActions>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }


    override fun setModel() {
        dataBinding.viewModel = model
    }


}