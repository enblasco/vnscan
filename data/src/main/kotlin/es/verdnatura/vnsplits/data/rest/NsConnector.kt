package es.verdnatura.vnsplits.data.rest


import android.app.Application
import es.verdnatura.vnsplits.domain.parser.Serializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 16/2/17.
 */
@Singleton
class NsConnector {

    var ENDPOINT = Host.getHost()

    val CONNECTION_TIMEOUT: Long = 10

    val application: Application
    val serializer: Serializer
    lateinit var vnAdapter: Retrofit

    constructor(application: Application) {
        this.application = application
        createApi("user", "pass")
        serializer = Serializer()
    }

    fun createApi(user: String, pass: String) {

        var client: OkHttpClient = OkHttpClient
                .Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(NsInterceptor(application, user, pass))
                .build()


        vnAdapter = Retrofit
                .Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

}