package es.verdnatura.vnsplits.data.rest

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
interface SecurityApi {

    @POST("security/login")
    fun login(@Body vararg  args: Any): Observable<Int>

}