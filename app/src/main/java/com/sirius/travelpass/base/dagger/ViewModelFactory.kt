package com.sirius.travelpass.base.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirius.travelpass.ui.activities.auth.AuthActivityModel
import com.sirius.travelpass.ui.activities.loader.LoaderActivityModel
import com.sirius.travelpass.ui.activities.main.MainActivityModel
import com.sirius.travelpass.ui.activities.splash.SplashActivityModel
import com.sirius.travelpass.ui.activities.tutorial.TutorialActivityModel
import com.sirius.travelpass.ui.auth.auth_first.AuthFirstViewModel
import com.sirius.travelpass.ui.auth.auth_fourth.AuthFourthViewModel
import com.sirius.travelpass.ui.auth.auth_second.AuthSecondViewModel
import com.sirius.travelpass.ui.auth.auth_second_second.AuthSecondSecondViewModel
import com.sirius.travelpass.ui.auth.auth_third.AuthThirdViewModel
import com.sirius.travelpass.ui.auth.auth_third_identity.AuthThirdChooseIdViewModel
import com.sirius.travelpass.ui.auth.auth_third_identity.AuthThirdIdentityViewModel
import com.sirius.travelpass.ui.auth.auth_third_third.AuthThirdThirdViewModel
import com.sirius.travelpass.ui.auth.auth_zero.AuthZeroViewModel
import com.sirius.travelpass.ui.chats.ChatsViewModel
import com.sirius.travelpass.ui.contacts.ContactsViewModel
import com.sirius.travelpass.ui.credentials.CredentialsViewModel
import com.sirius.travelpass.ui.menu.CredentialFaceViewModel
import com.sirius.travelpass.ui.menu.MenuViewModel
import com.sirius.travelpass.ui.profile.ProfileViewModel
import com.sirius.travelpass.ui.qrcode.ScanQrViewModel
import com.sirius.travelpass.ui.qrcode.ShowQrViewModel
import com.sirius.travelpass.ui.validating.ErrorViewModel
import com.sirius.travelpass.ui.validating.ValidatingViewModel


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

    @Binds
    @IntoMap
    @ViewModelKey(LoaderActivityModel::class)
    internal abstract fun bindLoaderActivityModel(viewModel: LoaderActivityModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TutorialActivityModel::class)
    internal abstract fun bindTutorialActivityModel(viewModel: TutorialActivityModel): ViewModel

    /**
     * Fragments viewModel Here
     */

    @Binds
    @IntoMap
    @ViewModelKey(AuthZeroViewModel::class)
    internal abstract fun bindAuthZeroViewModel(viewModel: AuthZeroViewModel): ViewModel


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
    @ViewModelKey(AuthSecondSecondViewModel::class)
    internal abstract fun bindAuthSecondSecondViewModel(viewModel: AuthSecondSecondViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdViewModel::class)
    internal abstract fun bindAuthThirdViewModel(viewModel: AuthThirdViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdThirdViewModel::class)
    internal abstract fun bindAuthThirdThirdViewModel(viewModel: AuthThirdThirdViewModel): ViewModel




    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdIdentityViewModel::class)
    internal abstract fun bindAuthThirdIdentityViewModel(viewModel: AuthThirdIdentityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdChooseIdViewModel::class)
    internal abstract fun bindAuthThirdChooseIdViewModel(viewModel: AuthThirdChooseIdViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(AuthFourthViewModel::class)
    internal abstract fun bindAuthFourthViewModel(viewModel: AuthFourthViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    internal abstract fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    internal abstract fun bindContactsViewModel(viewModel: ContactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CredentialsViewModel::class)
    internal abstract fun bindCredentialsViewModel(viewModel: CredentialsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CredentialFaceViewModel::class)
    internal abstract fun bindCredentialFaceViewModel(viewModel: CredentialFaceViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ScanQrViewModel::class)
    internal abstract fun bindScanQrViewModel(viewModel: ScanQrViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowQrViewModel::class)
    internal abstract fun bindShowQrViewModel(viewModel: ShowQrViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatsViewModel::class)
    internal abstract fun bindChatsViewModel(viewModel: ChatsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ValidatingViewModel::class)
    internal abstract fun bindValidatingViewModel(viewModel: ValidatingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ErrorViewModel::class)
    internal abstract fun bindErrorViewModel(viewModel: ErrorViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

}