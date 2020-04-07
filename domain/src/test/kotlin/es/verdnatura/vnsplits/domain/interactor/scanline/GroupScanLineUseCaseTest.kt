package es.verdnatura.vnsplits.domain.interactor.scanline

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
class GroupScanLineUseCaseTest: ScanLineBaseTest() {

    lateinit var groupScanLineUseCase: GroupLinesUseCase

    @Before
    fun setUp(){
        groupScanLineUseCase = GroupLinesUseCase(uiThread, thread)
    }

    @Test
    fun groupLinesTest(){
        val newLines = groupScanLineUseCase.groupLines(lines)
        assertEquals(newLines.size, 12)
    }

}