package es.verdnatura.vnsplits.data.rest

import es.verdnatura.vnsplits.data.entity.ScanDto
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
interface ScanApi {

    @POST("scan/list_scans")
    fun listScans(@Body vararg  args: Any): Observable<List<ScanDto>>

    @POST("scan/add_scan")
    fun addScan(@Body vararg  args: Any): Observable<Int>

    @POST("scan/delete_scan")
    fun deleteScan(@Body vararg  args: Any): Observable<Void>

    @POST("scan/create_source")
    fun createSource(@Body vararg  args: Any): Observable<Void>

    @POST("scan/get_warehouses")
    fun getWarehouses(): Observable<List<String>>

    @POST("scan/get_version")
    fun getVersion(): Observable<Int>

    @POST("bin/vnsplits.apk")
    fun getApk(): Observable<Response<ResponseBody>>

}