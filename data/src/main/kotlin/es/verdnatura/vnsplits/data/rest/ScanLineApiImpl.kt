package es.verdnatura.vnsplits.data.rest

import android.app.Application
import es.verdnatura.core.UtilsResources
import es.verdnatura.vnsplits.data.R
import es.verdnatura.vnsplits.data.database.ScanLineDatabase
import es.verdnatura.vnsplits.data.exceptions.ServerException
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.domain.parser.Serializer
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 9/6/17.
 */
class ScanLineApiImpl: ScanLineRepository {

    val application: Application
    val serializer: Serializer
    val scanLineApi: ScanLineApi
    val vnConnector: NsConnector
    val scanLineDatabase: ScanLineDatabase

    @Inject
    constructor(app: Application, nsConnector: NsConnector, sld: ScanLineDatabase) {
        this.application = app
        this.vnConnector = nsConnector
        this.scanLineDatabase = sld
        scanLineApi = vnConnector.vnAdapter.create(ScanLineApi::class.java)
        serializer = Serializer()
    }

    private fun getError(errorId: Int): String {
        return UtilsResources.getResourceString(application, errorId)
    }

    private fun getStringError(message:String): String{
        return message;
    }

    private fun getError(throwable: Throwable, message: Int){
        getStringError(throwable.message.toString())
        //if(throwable is ServerException) throwable.message =  getError(message)
    }

    override fun listScanLine(scan: Int): Observable<List<ScanLine>> {
        return scanLineApi.listScanLine(scan)
                .map { lines -> serializer.serialize(lines, List::class.java) }
                .map { json -> serializer.deserializeList(json, ScanLine::class.java) }
                .map { scans -> scans + getScansDatabase(scan) }
                .doOnError { t -> getError(t, R.string.error_getting_scan_lines) }
    }

    override fun createLines(scan: Int): Observable<List<ScanLine>> {
        val lines = scanLineDatabase.listScanLine(scan)
        val codes = lines.map { line -> line.Code }
        val ids = lines.map { line -> line.Id }
        var slines: List<ScanLine> = arrayListOf()

        return scanLineApi.createLines(scan, codes)
                .map { slines -> serializer.serialize(slines, List::class.java) }
                .map { json -> serializer.deserializeList(json, ScanLine::class.java) }
                .map { ll -> slines = ll}
                .map { _ -> scanLineDatabase.deleteLines(ids)}
                .map { _ -> slines }
                .doOnError { t -> getError(t, R.string.error_creating_scan_lines) }
    }

    override fun deleteLines(lines: List<ScanLine>): Observable<Boolean> {
        val separatedLines = lines.partition { line -> line.Db == true }
        val databaseIds = separatedLines.first.map { line -> line.Id }
        val serverIds = separatedLines.second.map { line -> line.Id }

        return scanLineApi.deleteLines(serverIds)
                .map { _ -> if(databaseIds.size > 0) scanLineDatabase.deleteLines(databaseIds) }
                .map { true }
                .doOnError { t -> getError(t, R.string.error_deleting_scan_lines) }
    }

    override fun insertScanLine(scan: Int, code: String): Observable<Int> {
        return Observable.just(scanLineDatabase.createLines(scan, code)).doOnError {  }
    }

    fun getScansDatabase(scan: Int): List<ScanLine> {
        return scanLineDatabase.listScanLine(scan)
    }
}