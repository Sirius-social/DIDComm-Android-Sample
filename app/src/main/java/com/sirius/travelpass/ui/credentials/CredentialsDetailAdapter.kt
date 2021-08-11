package com.sirius.travelpass.ui.credentials

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.travelpass.R
import com.sirius.travelpass.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.travelpass.databinding.ViewItemsCredentialsBinding
import com.sirius.travelpass.databinding.ViewItemsCredentialsDetailBinding
import com.sirius.travelpass.models.ui.ItemCredentials
import com.sirius.travelpass.models.ui.ItemCredentialsDetails

class CredentialsDetailAdapter :
    SimpleBaseRecyclerViewAdapter<ItemCredentialsDetails, CredentialsDetailAdapter.CredentialsDetailsViewHolder>() {



    class CredentialsDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsCredentialsDetailBinding? = DataBindingUtil.bind<ViewItemsCredentialsDetailBinding>(itemView)
        fun bind(item: ItemCredentialsDetails) {
            binding?.item = item

        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_credentials_detail
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): CredentialsDetailsViewHolder {
        return CredentialsDetailsViewHolder(getInflatedView(getLayoutRes(),parent, false))
    }

    override fun onBind(holder: CredentialsDetailsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
    }

}