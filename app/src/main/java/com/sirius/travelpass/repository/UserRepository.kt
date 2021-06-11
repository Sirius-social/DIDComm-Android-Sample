package com.sirius.travelpass.repository

import com.sirius.travelpass.base.AppExecutors
import com.sirius.travelpass.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val appExecutors: AppExecutors)  {

    var myUser = User()
}