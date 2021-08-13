package com.sirius.travelpass.ui.chats

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.travelpass.R
import com.sirius.travelpass.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.travelpass.databinding.ViewItemsCredentialsBinding
import com.sirius.travelpass.databinding.ViewItemsCredentialsDetailBinding
import com.sirius.travelpass.databinding.ViewItemsQuestionAnswerBinding
import com.sirius.travelpass.models.ui.ItemCredentials
import com.sirius.travelpass.models.ui.ItemCredentialsDetails
import com.sirius.travelpass.models.ui.ItemQuestionAnswer

class QuestionAnswerAdapter :
    SimpleBaseRecyclerViewAdapter<ItemQuestionAnswer, QuestionAnswerAdapter.QuestionAnswerViewHolder>() {



    class QuestionAnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsQuestionAnswerBinding? = DataBindingUtil.bind<ViewItemsQuestionAnswerBinding>(itemView)
        fun bind(item: ItemQuestionAnswer) {
            binding?.item = item
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_question_answer
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): QuestionAnswerViewHolder {
        return QuestionAnswerViewHolder(getInflatedView(getLayoutRes(),parent, false))
    }

    override fun onBind(holder: QuestionAnswerViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
        holder?.itemView?.setOnClickListener {
            onAdapterItemClick?.onItemClick(item)
        }
    }

}