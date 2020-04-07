package es.verdnatura.vnsplits.domain.repository

import es.verdnatura.vnsplits.domain.entity.ScanLine
import io.reactivex.Observable

/**
 * Created by Enrique Blasco Blanquer on 9/6/17.
 */
interface ScanLineRepository {

    fun createLines(scan: Int): Observable<List<ScanLine>>
    fun deleteLines(lines: List<ScanLine>): Observable<Boolean>
    fun listScanLine(scan: Int): Observable<List<ScanLine>>
    fun insertScanLine(scan: Int, code: String): Observable<Int>
}