package com.sirius.travelpass.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.travelpass.R
import com.sirius.travelpass.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.travelpass.databinding.ViewActionItemsBinding
import com.sirius.travelpass.databinding.ViewItemsCredentialsBinding
import com.sirius.travelpass.models.ui.ItemActions


import com.sirius.travelpass.models.ui.ItemCredentials


class ActionsAdapter :
    SimpleBaseRecyclerViewAdapter<ItemActions, ActionsAdapter.ActionsViewHolder>() {


    override fun onBind(holder: ActionsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
        holder?.itemView?.setOnClickListener {
            onAdapterItemClick?.onItemClick(item)
        }
    }


    class ActionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewActionItemsBinding? = DataBindingUtil.bind<ViewActionItemsBinding>(itemView)
        fun bind(item: ItemActions) {
            binding?.model = item
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_action_items
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): ActionsViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return ActionsViewHolder(view)
    }


}