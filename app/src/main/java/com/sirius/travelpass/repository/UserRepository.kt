package com.sirius.travelpass.repository

import androidx.annotation.WorkerThread
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppExecutors
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.models.User
import com.sirius.travelpass.repository.dao.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val appExecutors: AppExecutors)  {

    var myUser = User()
    //val userDao = App.getInstance().db.userDao()
    init {
        setupUserFromPref()
    }

    fun setupUserFromPref(){
        val user =  AppPref.getInstance().getUser()
        user?.let {
            myUser = it
        }
    }

    fun saveUserToPref(){
        AppPref.getInstance().setUser(myUser)
    }

    fun logout(){
        AppPref.getInstance().setUser(null)
    }


    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    //val allWords: Flow<List<User>> = userDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: User) {
        //wordDao.insert(word)
    }
}