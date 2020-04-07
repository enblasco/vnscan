package es.verdnatura.vnsplits.presentation.di.modules

import dagger.Module
import dagger.Provides
import es.verdnatura.vnsplits.domain.interactor.login.LoginUseCase
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.presenter.LoginPresenter
import es.verdnatura.vnsplits.presentation.view.activity.LoginActivity
import io.reactivex.Scheduler
import javax.inject.Named

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
@Module
class LoginModule(private val activity: LoginActivity) {

    @PerActivity @Provides fun getLoginPresenter(loginUseCase: LoginUseCase) = LoginPresenter(loginUseCase)

    @Provides
    @PerActivity
    fun getLoginUseCase(securityRepository: SecurityRepository,
                        @Named("ui_thread") uiThread: Scheduler,
                        @Named("executor_thread") executorThread: Scheduler): LoginUseCase {
        return LoginUseCase(securityRepository, uiThread, executorThread)
    }
}