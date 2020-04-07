package es.verdnatura.vnsplits.test.di

import dagger.Module
import dagger.Provides
import es.verdnatura.vnsplits.domain.interactor.login.LoginUseCase
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import es.verdnatura.vnsplits.presentation.presenter.LoginPresenter
import io.reactivex.Scheduler
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 30/6/17.
 */
@Module
class LoginModuleTest() {

    @Singleton
    @Provides
    fun getLoginPresenter(loginUseCase: LoginUseCase): LoginPresenter {
        return Mockito.mock(LoginPresenter::class.java)
    }

    @Singleton
    @Provides
    fun getLoginUseCase(securityRepository: SecurityRepository, uiThread: Scheduler, executorThread: Scheduler): LoginUseCase {
        return Mockito.mock(LoginUseCase::class.java)
    }

}