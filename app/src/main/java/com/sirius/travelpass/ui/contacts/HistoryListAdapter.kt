package com.sirius.travelpass.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.travelpass.R
import com.sirius.travelpass.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.travelpass.databinding.ViewItemsContactsBinding
import com.sirius.travelpass.databinding.ViewItemsCredentialsHistoryBinding
import com.sirius.travelpass.databinding.ViewItemsTagsBinding
import com.sirius.travelpass.models.ui.ItemContacts
import com.sirius.travelpass.models.ui.ItemCredentials
import com.sirius.travelpass.models.ui.ItemTags


class HistoryListAdapter() :
    SimpleBaseRecyclerViewAdapter<ItemCredentials, HistoryListAdapter.CredentialsHistoryViewHolder>() {


    override fun onBind(holder: CredentialsHistoryViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
    }


    class CredentialsHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsCredentialsHistoryBinding? = DataBindingUtil.bind<ViewItemsCredentialsHistoryBinding>(itemView)
        fun bind(item: ItemCredentials) {
            binding?.model = item
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_credentials_history
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): CredentialsHistoryViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return CredentialsHistoryViewHolder(view)
    }


}