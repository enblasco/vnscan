package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.TestOpen
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
@TestOpen
class ShowChildsUseCase: UseCase<List<Scan>, ParamsShowHideChilds> {

    @Inject
    constructor(uiThread: Scheduler, executorThread: Scheduler) : super(uiThread, executorThread)

    override fun buildUseCaseObservable(params: ParamsShowHideChilds): Observable<List<Scan>> {
        showChilds(params)
        return Observable.just(params.scans)
    }

    fun showChilds(params: ParamsShowHideChilds){
        val index = params.scans.indexOf(params.scan)
        params.scans[index].Displayed = true
        params.scans.addAll(index + 1, params.scan.Childs)
    }

}