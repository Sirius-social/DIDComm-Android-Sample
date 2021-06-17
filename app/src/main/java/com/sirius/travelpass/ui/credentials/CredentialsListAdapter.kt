package com.sirius.travelpass.ui.credentials

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.travelpass.R
import com.sirius.travelpass.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.travelpass.databinding.ViewItemsCredentialsBinding


import com.sirius.travelpass.models.ui.ItemCredentials


class CredentialsListAdapter :
    SimpleBaseRecyclerViewAdapter<ItemCredentials, CredentialsListAdapter.CredentialsViewHolder>() {


    override fun onBind(holder: CredentialsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
    }


    class CredentialsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsCredentialsBinding? = DataBindingUtil.bind<ViewItemsCredentialsBinding>(itemView)
        fun bind(item: ItemCredentials) {
            binding?.model = item
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_credentials
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): CredentialsViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return CredentialsViewHolder(view)
    }


}