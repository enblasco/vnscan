package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.TestOpen
import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 25/5/17.
 */
@TestOpen
class AddScanUseCase: UseCase<Int, AddScanUseCase.Params> {

    private val repository: ScanRepository

    @Inject
    constructor(repository: ScanRepository, uiThread: Scheduler, thread: Scheduler) : super(uiThread, thread) {
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: Params): Observable<Int> {
        return repository.addScan(params.type, params.parent, params.name)
    }

    class Params private constructor(val type: String, val parent: Int = 0, val name: String = ""){
        companion object{
            fun forAddScan(type: String, parent: Int = 0, name: String = ""): Params {
                return Params(type, parent, name)
            }
        }
    }

}