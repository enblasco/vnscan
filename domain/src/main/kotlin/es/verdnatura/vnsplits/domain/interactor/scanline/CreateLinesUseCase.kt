package es.verdnatura.vnsplits.domain.interactor.scanline

import es.verdnatura.vnsplits.domain.TestOpen
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 13/6/17.
 */
@TestOpen
class CreateLinesUseCase: UseCase<List<ScanLine>, CreateLinesUseCase.Params> {

    val repository: ScanLineRepository

    @Inject
    constructor(repository: ScanLineRepository, uiThread: Scheduler, thread: Scheduler): super(uiThread, thread){
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: Params): Observable<List<ScanLine>> {
        return repository.createLines(params.scan)
                .map { ll -> deleteLines(params.lines) + ll }
    }

    class Params private constructor(val scan: Int, val lines: List<ScanLine>){
        companion object{
            fun forCreate(scan: Int, lines: List<ScanLine>): Params{
                return Params(scan, lines)
            }
        }
    }

    fun deleteLines(lines: List<ScanLine>): List<ScanLine> {
        return lines.partition { line -> line.Db == true}
                .second
    }

}