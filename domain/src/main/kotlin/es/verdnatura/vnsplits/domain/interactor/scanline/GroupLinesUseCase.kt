package es.verdnatura.vnsplits.domain.interactor.scanline

import es.verdnatura.vnsplits.domain.entity.ScanLine

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class GroupLinesUseCase: es.verdnatura.vnsplits.domain.interactor.UseCase<List<ScanLine>, GroupLinesUseCase.Params> {

    @javax.inject.Inject
    constructor(uiThread: io.reactivex.Scheduler, thread: io.reactivex.Scheduler): super(uiThread, thread)

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.interactor.scanline.GroupLinesUseCase.Params): io.reactivex.Observable<List<ScanLine>> {
        return io.reactivex.Observable.just(groupLines(params.lines))
    }

    fun groupLines(lines: List<es.verdnatura.vnsplits.domain.entity.ScanLine>): List<es.verdnatura.vnsplits.domain.entity.ScanLine>{
        var groupedLines = mutableListOf<es.verdnatura.vnsplits.domain.entity.ScanLine>()
        lines.groupBy { line -> line.Code }
                .map { g ->  g.value}
                .map { list ->  groupedLines.add(es.verdnatura.vnsplits.domain.entity.ScanLine(list[0].Code, list[0].Value, list[0].Info, list.size))}
        return groupedLines
    }

    class Params private constructor(val lines: List<es.verdnatura.vnsplits.domain.entity.ScanLine>){
        companion object{
            fun forGroupLines(lines: List<es.verdnatura.vnsplits.domain.entity.ScanLine>): es.verdnatura.vnsplits.domain.interactor.scanline.GroupLinesUseCase.Params {
                return es.verdnatura.vnsplits.domain.interactor.scanline.GroupLinesUseCase.Params(lines)
            }
        }
    }

}