package com.sirius.travelpass.base.dagger;

import android.app.Application
import com.sirius.travelpass.ui.activities.auth.AuthActivity
import com.sirius.travelpass.ui.activities.main.MainActivity
import com.sirius.travelpass.ui.activities.splash.SplashActivity
import com.sirius.travelpass.ui.auth.auth_first.AuthFirstFragment
import com.sirius.travelpass.ui.auth.auth_fourth.AuthFourthFragment
import com.sirius.travelpass.ui.auth.auth_second.AuthSecondFragment
import com.sirius.travelpass.ui.auth.auth_third.AuthThirdFragment
import com.sirius.travelpass.ui.auth.auth_third_identity.AuthThirdIChooseIdFragment
import com.sirius.travelpass.ui.auth.auth_third_identity.AuthThirdIdentityFragment
import com.sirius.travelpass.ui.chats.ChatsFragment
import com.sirius.travelpass.ui.contacts.ContactsFragment
import com.sirius.travelpass.ui.credentials.CredentialsFragment
import com.sirius.travelpass.ui.menu.MenuFragment
import com.sirius.travelpass.ui.qrcode.ScanQrFragment
import com.sirius.travelpass.ui.qrcode.ShowQrFragment


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
    fun inject(fragment: AuthThirdIdentityFragment)
    fun inject(fragment: AuthThirdIChooseIdFragment)
    fun inject(fragment: AuthFourthFragment)

    fun inject(fragment: CredentialsFragment)
    fun inject(fragment: ContactsFragment)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: ScanQrFragment)
    fun inject(fragment: ShowQrFragment)
    fun inject(fragment: ChatsFragment)

}