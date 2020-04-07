package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.TestOpen
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.params.ParamsScans
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class ScanListUseCase : es.verdnatura.vnsplits.domain.interactor.UseCase<List<Scan>, ParamsScans> {

    private val repository: es.verdnatura.vnsplits.domain.repository.ScanRepository

    @javax.inject.Inject
    constructor(repository: es.verdnatura.vnsplits.domain.repository.ScanRepository, uiThread: io.reactivex.Scheduler, mExecutorThread: io.reactivex.Scheduler) : super(uiThread, mExecutorThread) {
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.params.ParamsScans): io.reactivex.Observable<List<Scan>> {
        return this.repository.getScans(params.type)
    }

}