package com.sirius.travelpass.base.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirius.travelpass.ui.activities.auth.AuthActivityModel
import com.sirius.travelpass.ui.activities.main.MainActivityModel
import com.sirius.travelpass.ui.activities.splash.SplashActivityModel
import com.sirius.travelpass.ui.auth.auth_first.AuthFirstViewModel
import com.sirius.travelpass.ui.auth.auth_second.AuthSecondViewModel
import com.sirius.travelpass.ui.auth.auth_third.AuthThirdViewModel


import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * Add all viewModel Here
     */


    /**
     * Activity viewModel Here
     */


    @Binds
    @IntoMap
    @ViewModelKey(MainActivityModel::class)
    internal abstract fun bindMainActivityModel(viewModel: MainActivityModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthActivityModel::class)
    internal abstract fun bindAuthActivityModel(viewModel: AuthActivityModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityModel::class)
    internal abstract fun bindSplashActivityModel(viewModel: SplashActivityModel): ViewModel


    /**
     * Fragments viewModel Here
     */



    @Binds
    @IntoMap
    @ViewModelKey(AuthFirstViewModel::class)
    internal abstract fun bindAuthFirstViewModel(viewModel: AuthFirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthSecondViewModel::class)
    internal abstract fun bindAuthSecondViewModel(viewModel: AuthSecondViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdViewModel::class)
    internal abstract fun bindAuthThirdViewModel(viewModel: AuthThirdViewModel): ViewModel



}