package es.verdnatura.vnsplits.domain.interactor.scanline

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository

import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ListScanLineUseCase : es.verdnatura.vnsplits.domain.interactor.UseCase<List<ScanLine>, ListScanLineUseCase.Params> {

    private val repository: es.verdnatura.vnsplits.domain.repository.ScanLineRepository

    @javax.inject.Inject
    constructor(repository: es.verdnatura.vnsplits.domain.repository.ScanLineRepository, uiThread: io.reactivex.Scheduler, thread: io.reactivex.Scheduler): super(uiThread, thread){
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.interactor.scanline.ListScanLineUseCase.Params): io.reactivex.Observable<List<ScanLine>> {
        return repository.listScanLine(params.scan)
    }

    class Params(val scan: Int){
        companion object{
            fun forGetScanLines(scan: Int): es.verdnatura.vnsplits.domain.interactor.scanline.ListScanLineUseCase.Params {
                return es.verdnatura.vnsplits.domain.interactor.scanline.ListScanLineUseCase.Params(scan)
            }
        }
    }

}