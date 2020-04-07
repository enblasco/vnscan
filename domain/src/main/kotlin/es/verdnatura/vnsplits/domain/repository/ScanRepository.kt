package es.verdnatura.vnsplits.domain.repository

import es.verdnatura.vnsplits.domain.entity.Scan
import io.reactivex.Observable
import okio.BufferedSource

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
interface ScanRepository {

    fun getScans(type: String): Observable<List<Scan>>
    fun addScan(type: String, parent: Int = 0, name: String = ""): Observable<Int>
    fun deleteScan(id: Int): Observable<Void>
    fun createSource(source: String): Observable<Void>
    fun getWarehouses(): Observable<List<String>>
    fun getVersion(): Observable<Int>
    fun getApk(): Observable<BufferedSource>
}