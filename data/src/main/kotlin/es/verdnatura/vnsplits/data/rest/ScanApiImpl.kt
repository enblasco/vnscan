package es.verdnatura.vnsplits.data.rest

import android.app.Application
import es.verdnatura.core.UtilsResources
import es.verdnatura.vnsplits.data.R
import es.verdnatura.vnsplits.data.entity.ScanLineDto
import es.verdnatura.vnsplits.data.exceptions.ServerException
import es.verdnatura.vnsplits.domain.parser.Serializer
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import okio.BufferedSource
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
class ScanApiImpl: ScanRepository {

    val application: Application
    val serializer: Serializer
    val scanApi: ScanApi
    val vnConnector: NsConnector

    @Inject
    constructor(app: Application, nsConnector: NsConnector) {
        application = app
        vnConnector = nsConnector
        scanApi = vnConnector.vnAdapter.create(ScanApi::class.java)
        serializer = Serializer()
    }

    private fun getError(errorId: Int): String {
        return UtilsResources.getResourceString(application, errorId)
    }

    private fun getError(throwable: Throwable, message: Int){
        if(throwable is ServerException) throwable.message =  getError(message)
    }

    override fun getScans(type: String): Observable<List<Scan>> {
        return scanApi.listScans(type)
                .map { scans -> serializer.serialize(scans, List::class.java) }
                .map { json -> serializer.deserializeList(json, Scan::class.java)}
                .doOnError { t -> getError(t, R.string.error_getting_scans) }
    }

    override fun addScan(type: String, parent: Int, name: String): Observable<Int> {
        return scanApi.addScan(type, parent, name)
                .doOnError { t -> getError(t, R.string.error_add_scan) }
    }

    override fun deleteScan(id: Int): Observable<Void> {
        return scanApi.deleteScan(id)
                .doOnError { t -> getError(t, R.string.error_delete_scans) }
    }

    override fun createSource(source: String): Observable<Void> {
        return scanApi.createSource(source)
    }

    override fun getWarehouses(): Observable<List<String>> {
        return scanApi.getWarehouses().doOnError { t -> getError(t, R.string.error_getting_warehouses) }
    }

    override fun getVersion(): Observable<Int> {
        return scanApi.getVersion()
    }

    override fun getApk(): Observable<BufferedSource> {
        return scanApi.getApk().map { r -> r.body().source() }
                .doOnError { t -> getError(t, R.string.error_getting_update) }
    }

}