package com.sirius.travelpass.ui.contacts

import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment
import com.sirius.travelpass.databinding.*
import com.sirius.travelpass.models.ui.ItemContacts
import com.sirius.travelpass.ui.chats.ChatsFragment


class ContactsFragment : BaseFragment<FragmentContactsBinding, ContactsViewModel>() {

    lateinit var adapter : ContactsListAdapter


    override fun setupViews() {
        super.setupViews()
        adapter =   ContactsListAdapter(model::onChatsClick, model::onDetailsClick)
        /*   historyAdapter!!.setOnAdapterItemClick {
               it?.let {
                   model.onItemClick(it)
               }
           }*/
        dataBinding.contactsRecyclerView.adapter = adapter
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_contacts
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })

        model.onChatClickLiveData.observe(this, Observer {
            it?.let {
                model.onChatClickLiveData.value = null
                baseActivity.pushPage(ChatsFragment.newInstance(it))
            }
        })

        model.onDetailsClickLiveData.observe(this, Observer {
            it?.let {
                model.onDetailsClickLiveData.value = null
            }
        })
    }

    private fun updateAdapter(data: List<ItemContacts>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }


    override fun setModel() {
        dataBinding.viewModel = model
    }


}