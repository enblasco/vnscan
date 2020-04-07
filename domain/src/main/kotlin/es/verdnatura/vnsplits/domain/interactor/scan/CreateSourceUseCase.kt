package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 27/6/17.
 */
class CreateSourceUseCase: UseCase<List<String>, CreateSourceUseCase.Params> {

    private val repository: ScanRepository

    @Inject
    constructor(repository: ScanRepository, uiThread: Scheduler, thread: Scheduler): super(uiThread, thread){
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: Params): Observable<List<String>> {
        return repository.createSource(params.source)
                .map { _ -> params.sources.add(params.source) }
                .map { _ -> params.sources.sort() }
                .map { _ -> params.sources }
    }

    class Params private constructor(val source: String, val sources: MutableList<String>){
        companion object{
            fun forCreate(source: String, sources: MutableList<String>): Params{
                return Params(source, sources)
            }
        }
    }

}