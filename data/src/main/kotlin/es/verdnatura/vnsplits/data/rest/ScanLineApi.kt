package es.verdnatura.vnsplits.data.rest

import es.verdnatura.vnsplits.data.entity.ScanLineDto
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Enrique Blasco Blanquer on 9/6/17.
 */
interface ScanLineApi {

    @POST("scan/list_scan_line")
    fun listScanLine(@Body vararg  args: Any): Observable<List<ScanLineDto>>

    @POST("scan/create_lines")
    fun createLines(@Body vararg  args: Any): Observable<List<ScanLineDto>>

    @POST("scan/delete_lines")
    fun deleteLines(@Body vararg  args: Any): Observable<String>

}