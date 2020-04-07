package es.verdnatura.vnsplits.domain.interactor.scanline

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
class ListScanLineUseCaseTest: ScanLineBaseTest() {

    lateinit var listScanLineUseCase: ListScanLineUseCase

    val scan = 1

    @Before
    fun setUp(){
        listScanLineUseCase = ListScanLineUseCase(repository, uiThread, thread)
    }

    @Test
    fun testIntereactionRepository(){
        listScanLineUseCase.buildUseCaseObservable(ListScanLineUseCase.Params.forGetScanLines(scan))
        verify(repository).listScanLine(scan)
        verifyNoMoreInteractions(repository)
        verifyZeroInteractions(uiThread)
        verifyZeroInteractions(thread)
    }

}