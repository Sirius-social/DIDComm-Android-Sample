package com.sirius.travelpass.ui.chats

import android.os.Bundle
import androidx.core.widget.addTextChangedListener

import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment
import com.sirius.travelpass.databinding.*
import com.sirius.travelpass.models.ui.ItemContacts
import com.sirius.travelpass.ui.chats.message.BaseItemMessage


class ChatsFragment : BaseFragment<FragmentChatsBinding, ChatsViewModel>() {


    companion object {
        fun newInstance(item: ItemContacts): ChatsFragment {
            val args = Bundle()
            args.putSerializable("item",item)
            val fragment = ChatsFragment()
            fragment.arguments = args
            return fragment
        }
    }


   val adapter  = MessagesListAdapter()

    override fun setupViews() {
        model.item = arguments?.getSerializable("item") as? ItemContacts
        super.setupViews()
        adapter.lifecycle = this
        dataBinding.messagesRecyclerView.adapter = adapter
        dataBinding.messageText.addTextChangedListener {
            model.messageText = it.toString()
            model.enableSendIcon()
        }

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_chats
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })
        model.enableSendIconLiveData.observe(this, Observer {
            dataBinding.sendIcon.isEnabled = it
            if(it){
                dataBinding.sendIcon.setColorFilter(App.getContext().getColor(R.color.blue))
            }else{
                dataBinding.sendIcon.setColorFilter(App.getContext().getColor(R.color.gray_text_hint))
            }
        })

        model.clearTextLiveData.observe(this, Observer {
            if(it){
                model.clearTextLiveData.value = false
                dataBinding.messageText.setText("")
            }
        })

        model.eventStoreLiveData.observe(this, Observer {
            model.updateList()
            dataBinding.messagesRecyclerView.scrollToPosition(adapter.itemCount)
        })
    }

    private fun updateAdapter(data: List<BaseItemMessage>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }



    override fun setModel() {
        dataBinding.viewModel = model
    }


}