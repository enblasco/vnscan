package es.verdnatura.vnsplits.presentation.di.components

import dagger.Subcomponent
import es.verdnatura.vnsplits.domain.interactor.login.LoginUseCase
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.di.modules.LoginModule
import es.verdnatura.vnsplits.presentation.presenter.LoginPresenter
import es.verdnatura.vnsplits.presentation.view.fragment.LoginFragment

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
@PerActivity
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)
    fun getLoginPresenter():LoginPresenter
    fun getLoginUseCase(): LoginUseCase

}