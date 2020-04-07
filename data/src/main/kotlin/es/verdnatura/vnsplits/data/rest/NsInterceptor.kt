package es.verdnatura.vnsplits.data.rest

import android.content.Context
import com.google.gson.Gson
import es.verdnatura.vnsplits.data.exceptions.NotFoundException
import es.verdnatura.vnsplits.data.exceptions.ServerError
import es.verdnatura.vnsplits.data.exceptions.ServerException
import es.verdnatura.vnsplits.data.exceptions.TimeoutException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.SocketTimeoutException

/**
 * Created by Enrique Blasco Blanquer on 1/3/17.
 */
class NsInterceptor(val context: Context, var user: String, var pass: String) : Interceptor{

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = getRequest(chain)

        try {

            val response = chain!!.proceed(request)
            when(response!!.code()){
                555 -> throw ServerException(Gson().fromJson(response.message(), Map::class.java)["Message"] as String)
                500 -> throw ServerError(context)
                404 -> throw NotFoundException(context)
            }

            return response

            } catch (exception: SocketTimeoutException) {
                  exception.printStackTrace()
                  throw TimeoutException(context)
            }

    }

    fun getRequest(chain: Interceptor.Chain?): Request{
        return chain!!.request()
                .newBuilder()
                .addHeader("Content-Type", "json")
                .addHeader("user", user)
                .addHeader("pass", pass)
                .addHeader("aplicacion", "SCANS")
                .build()
    }

}