package com.sirius.travelpass.base.dagger;

import android.app.Application
import com.sirius.travelpass.ui.activities.auth.AuthActivity
import com.sirius.travelpass.ui.activities.main.MainActivity
import com.sirius.travelpass.ui.activities.splash.SplashActivity
import com.sirius.travelpass.ui.auth.auth_first.AuthFirstFragment
import com.sirius.travelpass.ui.auth.auth_second.AuthSecondFragment
import com.sirius.travelpass.ui.auth.auth_third.AuthThirdFragment


import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelModule::class])
interface AppComponent {

    @dagger.Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application?): Builder?
        fun build(): AppComponent?
    }



    /**
     * Add all fragment with ViewModel Here
     */
    //Activities
    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: SplashActivity)


    //Fragments
    fun inject(fragment: AuthFirstFragment)
    fun inject(fragment: AuthSecondFragment)
    fun inject(fragment: AuthThirdFragment)

}