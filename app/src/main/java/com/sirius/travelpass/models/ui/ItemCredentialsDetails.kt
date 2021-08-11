package com.sirius.travelpass.models.ui

import java.util.*

class ItemCredentialsDetails {

    constructor() {

    }

    constructor(name: String, value: String) {
        this.name = name
        this.value = value
    }

    var name: String? = null
    var value: String? = null
}