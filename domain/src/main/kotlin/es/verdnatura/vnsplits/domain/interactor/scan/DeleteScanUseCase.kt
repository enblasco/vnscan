package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.entity.Scan

/**
 * Created by Enrique Blasco Blanquer on 26/5/17.
 */
@es.verdnatura.vnsplits.domain.TestOpen
class DeleteScanUseCase: es.verdnatura.vnsplits.domain.interactor.UseCase<List<Scan>, DeleteScanUseCase.Params> {

    private var repository: es.verdnatura.vnsplits.domain.repository.ScanRepository

    @javax.inject.Inject
    constructor(scanRepository: es.verdnatura.vnsplits.domain.repository.ScanRepository, uiThread: io.reactivex.Scheduler, mExecutor: io.reactivex.Scheduler): super(uiThread, mExecutor){
        repository = scanRepository
    }

    override fun buildUseCaseObservable(params: es.verdnatura.vnsplits.domain.interactor.scan.DeleteScanUseCase.Params): io.reactivex.Observable<List<Scan>> {
        return repository.deleteScan(params.id).map { deleteScans(params.id, params.scans as MutableList<es.verdnatura.vnsplits.domain.entity.Scan>) }
    }

    fun deleteScans(scan: Int, scans: MutableList<es.verdnatura.vnsplits.domain.entity.Scan>): List<es.verdnatura.vnsplits.domain.entity.Scan> {
        val listScans = scans.partition { s -> s.Parent != scan && s.Id != scan }
        listScans.second.forEach { s -> return deleteScans(s.Id, listScans.first.toMutableList()) }
        return listScans.first
    }

    class Params private constructor(val id: Int, val scans: List<es.verdnatura.vnsplits.domain.entity.Scan>){
        companion object{
            fun forId(id: Int, scans: List<es.verdnatura.vnsplits.domain.entity.Scan>): es.verdnatura.vnsplits.domain.interactor.scan.DeleteScanUseCase.Params {
                return es.verdnatura.vnsplits.domain.interactor.scan.DeleteScanUseCase.Params(id, scans)
            }
        }
    }

}


