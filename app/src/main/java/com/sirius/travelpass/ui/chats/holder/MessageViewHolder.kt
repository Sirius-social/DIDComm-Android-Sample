package com.sirius.travelpass.ui.chats.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sirius.travelpass.R
import com.sirius.travelpass.ui.chats.message.BaseItemMessage

open class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun bind(item: BaseItemMessage) {}

        companion object {
            fun getHolderFromType(type: Int, itemView: View): MessageViewHolder {

                if (type == BaseItemMessage.MessageType.Text.ordinal) {
                    return TextMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.Connect.ordinal) {
                    return ConnectMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.Connected.ordinal) {
                    return ConnectedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.ConnectError.ordinal) {
                    return ConnectedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.Offer.ordinal) {
                    return OfferMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.OfferAccepted.ordinal) {
                    return OfferAcceptedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.OfferError.ordinal) {
                    return OfferAcceptedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.Prover.ordinal) {
                    return ProverMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.ProverAccepted.ordinal) {
                    return ProverAcceptedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.ProverError.ordinal) {
                    return ProverAcceptedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.Question.ordinal) {
                    return QuestionMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.QuestionAccepted.ordinal) {
                    return QuestionAcceptedMessageViewHolder(itemView)
                }
                if (type == BaseItemMessage.MessageType.QuestionError.ordinal) {
                    return QuestionAcceptedMessageViewHolder(itemView)
                }
                return MessageViewHolder(itemView)
            }

            fun getLayoutResFromType(type: Int): Int {
                if (type == BaseItemMessage.MessageType.Text.ordinal) {
                    return R.layout.item_message_text
                }
                if (type == BaseItemMessage.MessageType.Connect.ordinal) {
                    return R.layout.item_message_connect
                }
                if (type == BaseItemMessage.MessageType.Connected.ordinal) {
                    return R.layout.item_message_connect_accepted
                }
                if (type == BaseItemMessage.MessageType.ConnectError.ordinal) {
                    return R.layout.item_message_connect_accepted
                }
                if (type == BaseItemMessage.MessageType.Offer.ordinal) {
                    return R.layout.item_message_offer
                }
                if (type == BaseItemMessage.MessageType.OfferAccepted.ordinal) {
                    return R.layout.item_message_offer_accepted
                }
                if (type == BaseItemMessage.MessageType.OfferError.ordinal) {
                    return R.layout.item_message_offer_accepted
                }
                if (type == BaseItemMessage.MessageType.Prover.ordinal) {
                    return R.layout.item_message_prover
                }

                if (type == BaseItemMessage.MessageType.ProverAccepted.ordinal) {
                    return R.layout.item_message_prover_accepted
                }
                if (type == BaseItemMessage.MessageType.ProverError.ordinal) {
                    return R.layout.item_message_prover_accepted
                }

                if (type == BaseItemMessage.MessageType.Question.ordinal) {
                    return R.layout.item_message_question
                }

                if (type == BaseItemMessage.MessageType.QuestionAccepted.ordinal) {
                    return R.layout.item_message_question_accepted
                }
                if (type == BaseItemMessage.MessageType.QuestionError.ordinal) {
                    return R.layout.item_message_question_accepted
                }
                
                return R.layout.item_message_text
            }
        }

    }