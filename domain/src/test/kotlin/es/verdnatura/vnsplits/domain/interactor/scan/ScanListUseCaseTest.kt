package es.verdnatura.vnsplits.domain.interactor.scan

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import es.verdnatura.vnsplits.domain.params.ParamsScans
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 2/6/17.
 */
class ScanListUseCaseTest: ScanBaseTest() {

    private lateinit var useCase: ScanListUseCase

    @Before
    fun setUp(){
        useCase = ScanListUseCase(repository, uiThread, thread)
    }

    @Test
    fun getScansTest(){
        useCase.buildUseCaseObservable(ParamsScans.forScan(""))
        verify(repository).getScans("")
        verifyNoMoreInteractions(repository)
        verifyZeroInteractions(uiThread)
        verifyZeroInteractions(thread)
    }

}