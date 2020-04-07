package es.verdnatura.vnsplits.test.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import es.verdnatura.vnsplits.domain.interactor.login.LoginUseCase
import es.verdnatura.vnsplits.presentation.presenter.LoginPresenter
import es.verdnatura.vnsplits.presentation.view.view.LoginView
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 15/5/17.
 */
class LoginPresenterTest {

    lateinit var loginPresenter: LoginPresenter
    val loginUseCase: LoginUseCase = mock()

    val loginView: LoginView = mock()

    @Before
    fun setUp(){
        loginPresenter = LoginPresenter(loginUseCase)
        loginPresenter.setView(loginView)
    }

    @Test
    fun loginTest(){
        loginPresenter.login("user", "pass")
        verify(loginView).showProgress()
        verify(loginUseCase).execute(any(), any())
    }

}