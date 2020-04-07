package es.verdnatura.vnsplits.domain.interactor.scanline

import es.verdnatura.vnsplits.domain.entity.ScanLine

/**
 * Created by Enrique Blasco Blanquer on 16/6/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class DeleteScanLineUseCase: es.verdnatura.vnsplits.domain.interactor.UseCase<List<ScanLine>, DeleteScanLineUseCase.Params> {

    val repository: es.verdnatura.vnsplits.domain.repository.ScanLineRepository

    constructor(repository: es.verdnatura.vnsplits.domain.repository.ScanLineRepository, uiThread: io.reactivex.Scheduler, thread: io.reactivex.Scheduler): super(uiThread, thread){
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.interactor.scanline.DeleteScanLineUseCase.Params): io.reactivex.Observable<List<ScanLine>> {
        val partitionLines = params.lines.partition { line -> line.Delete == true }
        return repository.deleteLines(partitionLines.first)
                .map { _ -> partitionLines.second }
    }

    class Params private constructor(val lines: List<es.verdnatura.vnsplits.domain.entity.ScanLine>){
        companion object{
            fun forDeleteLines(lines: List<es.verdnatura.vnsplits.domain.entity.ScanLine>): es.verdnatura.vnsplits.domain.interactor.scanline.DeleteScanLineUseCase.Params {
                return es.verdnatura.vnsplits.domain.interactor.scanline.DeleteScanLineUseCase.Params(lines)
            }
        }

    }

}