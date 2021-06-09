package com.sirius.travelpass.base.dagger;

import android.app.Application
import com.sirius.travelpass.ui.activites.main.MainActivity


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


    //Fragments
  //  fun inject(fragment: CabinetFragment)

}