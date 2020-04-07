package es.verdnatura.vnsplits.domain.interactor.scanline

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
class CreateLinesUseCaseTest: ScanLineBaseTest() {

    lateinit var createLinesUseCase: CreateLinesUseCase

    val scan = 1

    @Before
    fun setUp(){
        createLinesUseCase = CreateLinesUseCase(repository, uiThread, thread)
        lines.first().Db = true
        lines.last().Db = true
    }

    @Test
    fun testInteractionRepository(){
        `when`(repository.createLines(scan)).thenReturn(Observable.just(mutableListOf()))
        createLinesUseCase.buildUseCaseObservable(CreateLinesUseCase.Params.forCreate(scan, lines))
        verify(repository).createLines(scan)
        verifyNoMoreInteractions(repository)
        verifyZeroInteractions(uiThread)
        verifyZeroInteractions(thread)
    }

    @Test
    fun deleteLines(){
        val size = lines.size
        val newLines = createLinesUseCase.deleteLines(lines)
        assertEquals(size-2, newLines.size)
    }

}