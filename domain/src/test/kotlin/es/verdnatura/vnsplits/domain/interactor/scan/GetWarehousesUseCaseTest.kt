package es.verdnatura.vnsplits.domain.interactor.scan

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import es.verdnatura.vnsplits.domain.interactor.scan.GetWarehousesUseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class GetWarehousesUseCaseTest {

    private lateinit var getWarehousesUseCase: es.verdnatura.vnsplits.domain.interactor.scan.GetWarehousesUseCase

    val repository: es.verdnatura.vnsplits.domain.repository.ScanRepository = com.nhaarman.mockito_kotlin.mock()
    val uiThread: io.reactivex.Scheduler = com.nhaarman.mockito_kotlin.mock()
    val thread: io.reactivex.Scheduler = com.nhaarman.mockito_kotlin.mock()

    @org.junit.Before
    fun setUp(){
        getWarehousesUseCase = es.verdnatura.vnsplits.domain.interactor.scan.GetWarehousesUseCase(repository, uiThread, thread)
    }

    @org.junit.Test
    fun testGetWarehouses(){
        getWarehousesUseCase.buildUseCaseObservable({})
        com.nhaarman.mockito_kotlin.verify(repository).getWarehouses()
        com.nhaarman.mockito_kotlin.verifyNoMoreInteractions(repository)
        com.nhaarman.mockito_kotlin.verifyZeroInteractions(uiThread)
        com.nhaarman.mockito_kotlin.verifyZeroInteractions(thread)
    }

}