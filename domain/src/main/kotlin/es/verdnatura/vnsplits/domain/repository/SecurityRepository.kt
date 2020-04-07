package es.verdnatura.vnsplits.domain.repository

import io.reactivex.Observable

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
interface SecurityRepository {

    fun login(user: String, pass: String): Observable<Int>

    fun createApi(user: String, pass: String)
}