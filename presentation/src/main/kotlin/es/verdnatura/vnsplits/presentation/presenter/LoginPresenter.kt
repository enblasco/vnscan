package es.verdnatura.vnsplits.presentation.presenter

import es.verdnatura.vnsplits.domain.interactor.DefaultObserver
import es.verdnatura.vnsplits.domain.interactor.login.LoginUseCase
import es.verdnatura.vnsplits.presentation.view.view.BaseView
import es.verdnatura.vnsplits.presentation.view.view.LoginView
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
class LoginPresenter: BasePresenter {

    private lateinit var loginView: LoginView
    private val loginUseCase: LoginUseCase

    @Inject
    constructor(loginUseCase: LoginUseCase){
        this.loginUseCase = loginUseCase
    }

    override fun onDestroy() {}

    override fun setView(v: BaseView) {
        loginView = v as LoginView
    }

    fun login(user: String, pass: String){
        loginView.showProgress()
        loginUseCase.execute(LoginObserver(this), LoginUseCase.Params.forLogin(user, pass))
    }

    fun onFinishLogin(){
        loginView.saveUser()
        loginView.navigateToMain()
        loginView.hideProgress()
    }

    fun onErrorLogin(error: String){
        loginView.hideProgress()
        loginView.showError(error)
    }

    class LoginObserver(val loginPresenter: LoginPresenter) : DefaultObserver<Int>(){

        override fun onComplete() {
            super.onComplete()
            loginPresenter.onFinishLogin()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            loginPresenter.onErrorLogin(exception.message.toString())
        }

    }

}