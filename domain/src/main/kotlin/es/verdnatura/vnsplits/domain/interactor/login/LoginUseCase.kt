package es.verdnatura.vnsplits.domain.interactor.login

import es.verdnatura.vnsplits.domain.TestOpen
import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
@TestOpen
class LoginUseCase: UseCase<Int, LoginUseCase.Params> {

    val repository: SecurityRepository

    @Inject
    constructor(repository: SecurityRepository, uiThread: Scheduler, mExecutorThread: Scheduler) : super(uiThread, mExecutorThread) {
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: Params): Observable<Int> {
        this.repository.createApi(params.user, params.pass)
        return this.repository.login(params.user, params.pass)
    }

    class Params private constructor(val user: String, val pass: String) {
        companion object{
            fun forLogin(user: String, pass: String): Params {
                return Params(user, pass)
            }
        }
    }

}