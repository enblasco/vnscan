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
class DeleteScanLineUseCaseTest: ScanLineBaseTest() {

    lateinit var deleteScanLineUseCase: DeleteScanLineUseCase

    @Before
    fun setUp(){
        deleteScanLineUseCase = DeleteScanLineUseCase(repository, uiThread, thread)
    }

    @Test
    fun interactionRepositoryTest(){
        `when`(repository.deleteLines(lines)).thenReturn(Observable.just(true))
        deleteScanLineUseCase.buildUseCaseObservable(DeleteScanLineUseCase.Params.forDeleteLines(lines))
        verify(repository).deleteLines(lines)
        verifyNoMoreInteractions(repository)
        verifyZeroInteractions(uiThread)
        verifyZeroInteractions(thread)
    }

}