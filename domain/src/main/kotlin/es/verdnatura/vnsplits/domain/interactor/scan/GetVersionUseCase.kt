package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class GetVersionUseCase: UseCase<Boolean, GetVersionUseCase.Params> {

    val repository: ScanRepository

    @Inject
    constructor(repository: ScanRepository, uiThread: Scheduler, thread: Scheduler): super(uiThread, thread){
        this.repository = repository

    }

    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return repository.getVersion()
                .map { v ->  isNewVersion(v, params.version)}
    }

    fun isNewVersion(v: Int, version: Int): Boolean{
        if(v > version)
            return true
        return false
    }

    class Params private constructor(val version: Int){
        companion object{
            fun forGetVersion(v: Int):Params{
                return Params(v)
            }
        }
    }
}