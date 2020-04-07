package es.verdnatura.vnsplits.domain.interactor.scanline

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
class InsertScanLineUseCaseTest : ScanLineBaseTest() {

    lateinit var insertCanLineUseCase: InsertScanLineUseCase
    val scan = 1
    val code = "12345"

    @Before
    fun setUp(){
        insertCanLineUseCase = InsertScanLineUseCase(repository, uiThread, thread)
    }

    @Test
    fun interactionRepositoryTest(){
        `when`(repository.insertScanLine(scan, code)).thenReturn(Observable.just(2))
        insertCanLineUseCase.buildUseCaseObservable(InsertScanLineUseCase.Params.forInsertScan(scan, code))
        verify(repository).insertScanLine(scan, code)
        verifyNoMoreInteractions(repository)
        verifyZeroInteractions(uiThread)
        verifyZeroInteractions(thread)
    }

}