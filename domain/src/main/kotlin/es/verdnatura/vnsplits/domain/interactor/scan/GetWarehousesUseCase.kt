package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.TestOpen
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 7/6/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class GetWarehousesUseCase : es.verdnatura.vnsplits.domain.interactor.UseCase<List<String>, () -> Unit> {

    private val repository: es.verdnatura.vnsplits.domain.repository.ScanRepository

    @javax.inject.Inject
    constructor(repository: es.verdnatura.vnsplits.domain.repository.ScanRepository, uiThread: io.reactivex.Scheduler, thread: io.reactivex.Scheduler): super(uiThread, thread){
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: () -> Unit): io.reactivex.Observable<List<String>> {
        return repository.getWarehouses()
    }

}