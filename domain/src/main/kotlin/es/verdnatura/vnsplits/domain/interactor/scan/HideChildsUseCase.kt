package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds

/**
 * Created by Enrique Blasco Blanquer on 24/5/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class HideChildsUseCase : es.verdnatura.vnsplits.domain.interactor.UseCase<List<Scan>, ParamsShowHideChilds> {

    @javax.inject.Inject
    constructor(uiThread: io.reactivex.Scheduler, executorThread: io.reactivex.Scheduler) : super(uiThread, executorThread)

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds): io.reactivex.Observable<List<Scan>> {
        return io.reactivex.Observable.just(hideScans(params.scans, params.scan))
    }

    fun hideScans(scans: MutableList<es.verdnatura.vnsplits.domain.entity.Scan>, scan: es.verdnatura.vnsplits.domain.entity.Scan): List<es.verdnatura.vnsplits.domain.entity.Scan> {
        scan.Displayed = false
        val listScans = scans.partition { s -> s.Parent != scan.Id }
        listScans.second.forEach { s -> return hideScans(listScans.first.toMutableList(), s) }
        return listScans.first
    }
}