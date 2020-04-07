package es.verdnatura.vnsplits.domain.interactor.scanline

import es.verdnatura.vnsplits.domain.entity.ScanLine


/**
 * Created by Enrique Blasco Blanquer on 12/6/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class InsertScanLineUseCase : es.verdnatura.vnsplits.domain.interactor.UseCase<ScanLine, InsertScanLineUseCase.Params> {

    val repository: es.verdnatura.vnsplits.domain.repository.ScanLineRepository

    @javax.inject.Inject
    constructor(repository: es.verdnatura.vnsplits.domain.repository.ScanLineRepository, uiThread: io.reactivex.Scheduler, thread: io.reactivex.Scheduler): super(uiThread, thread){
        this.repository = repository

    }

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.interactor.scanline.InsertScanLineUseCase.Params): io.reactivex.Observable<ScanLine> {
        return repository
                .insertScanLine(params.scan, params.code)
                .map { id  -> es.verdnatura.vnsplits.domain.entity.ScanLine(id, params.code, true) }
    }


    class Params private constructor(val scan: Int, val code: String){
        companion object{
            fun forInsertScan(scan: Int, code: String): es.verdnatura.vnsplits.domain.interactor.scanline.InsertScanLineUseCase.Params {
                return es.verdnatura.vnsplits.domain.interactor.scanline.InsertScanLineUseCase.Params(scan, code)
            }
        }
    }

}