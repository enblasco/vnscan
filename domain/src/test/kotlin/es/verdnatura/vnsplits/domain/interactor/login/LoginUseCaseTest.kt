package es.verdnatura.vnsplits.domain.interactor.login

import com.nhaarman.mockito_kotlin.*
import es.verdnatura.vnsplits.domain.interactor.login.LoginUseCase
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 19/5/17.
 */
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase

    private val user = "user"
    private val pass = "pass"

    private val uiThread: Scheduler = mock()
    private val thread: Scheduler = mock()
    private val repository: SecurityRepository = mock()

    @Before
    fun setUp(){
        loginUseCase = LoginUseCase(repository, uiThread, thread)
    }

    @Test
    fun testLogin(){
        loginUseCase.buildUseCaseObservable(LoginUseCase.Params.forLogin(user, pass))
        verify(repository).createApi(user, pass)
        verify(repository).login(user, pass)
        verifyNoMoreInteractions(repository)
        verifyZeroInteractions(uiThread)
        verifyZeroInteractions(thread)
    }

}