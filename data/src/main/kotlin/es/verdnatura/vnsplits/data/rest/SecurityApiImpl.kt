package es.verdnatura.vnsplits.data.rest

import android.app.Application
import es.verdnatura.core.UtilsResources
import es.verdnatura.vnsplits.data.R
import es.verdnatura.vnsplits.data.exceptions.ServerException
import es.verdnatura.vnsplits.data.preferences.Preferences
import es.verdnatura.vnsplits.domain.parser.Serializer
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
@Singleton
class SecurityApiImpl: SecurityRepository {

    lateinit var securityApi: SecurityApi
    lateinit var application: Application
    lateinit var serializer: Serializer
    val preferences: Preferences
    val vnConnector: NsConnector

    @Inject
    constructor(nsConnector: NsConnector, preferences: Preferences) {
        vnConnector = nsConnector
        this.preferences = preferences
        if(preferences.getUser() != "")
            createApi(preferences.getUser(), preferences.getPass())
    }

    override fun createApi(user: String, pass: String) {
        vnConnector.createApi(user, pass)
        application = vnConnector.application
        serializer = vnConnector.serializer
        securityApi = vnConnector.vnAdapter.create(SecurityApi::class.java)
    }

    override fun login(user: String, pass: String): Observable<Int> {
        return securityApi.login(user, pass).doOnError {
            throwable -> if(throwable is ServerException) throwable.message = UtilsResources.getResourceString(application, R.string.error_login)
        }
    }
}