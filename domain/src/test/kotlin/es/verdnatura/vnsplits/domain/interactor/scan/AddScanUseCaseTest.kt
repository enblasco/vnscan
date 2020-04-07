package es.verdnatura.vnsplits.domain.interactor.scan

import com.nhaarman.mockito_kotlin.*
import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class AddScanUseCaseTest {

    private lateinit var addScanUseCase: es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase

    val repository: es.verdnatura.vnsplits.domain.repository.ScanRepository = com.nhaarman.mockito_kotlin.mock()
    val uiThread: io.reactivex.Scheduler = com.nhaarman.mockito_kotlin.mock()
    val thread: io.reactivex.Scheduler = com.nhaarman.mockito_kotlin.mock()

    @org.junit.Before
    fun setUp(){
        addScanUseCase = es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase(repository, uiThread, thread)
    }

    @org.junit.Test
    fun addScanTest(){
        addScanUseCase.buildUseCaseObservable(es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase.Params.Companion.forAddScan("BUYS", 0, "Scan"))
        com.nhaarman.mockito_kotlin.verify(repository).addScan("BUYS", 0, "Scan")
        com.nhaarman.mockito_kotlin.verifyNoMoreInteractions(repository)
        com.nhaarman.mockito_kotlin.verifyZeroInteractions(uiThread)
        com.nhaarman.mockito_kotlin.verifyZeroInteractions(thread)
    }

}