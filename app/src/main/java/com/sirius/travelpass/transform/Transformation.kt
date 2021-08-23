package com.sirius.travelpass.transform

import com.sirius.travelpass.models.ui.ItemContacts

interface Transformation  {

    fun toItemContacts() : ItemContacts? {
        return null
    }
}