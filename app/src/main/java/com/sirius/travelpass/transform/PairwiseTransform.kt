package com.sirius.travelpass.transform

import com.sirius.travelpass.models.ui.ItemContacts

import com.sirius.library.agent.pairwise.Pairwise
import java.util.*

class PairwiseTransform {

    companion object {
        fun pairwiseToItemContacts(pairwise: Pairwise) : ItemContacts{
           return ItemContacts(pairwise.their.did?:"",pairwise.their.label?:"", Date())
        }
    }
}