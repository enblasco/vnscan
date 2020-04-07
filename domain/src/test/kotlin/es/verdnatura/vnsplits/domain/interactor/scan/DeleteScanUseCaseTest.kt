package es.verdnatura.vnsplits.domain.interactor.scan

import org.mockito.Mockito.`when`

/**
 * Created by Enrique Blasco Blanquer on 1/6/17.
 */
class DeleteScanUseCaseTest : ScanBaseTest() {

    private lateinit var deleteUseCase: es.verdnatura.vnsplits.domain.interactor.scan.DeleteScanUseCase
    private val scan = 1

    @org.junit.Before
    fun setUp(){
        deleteUseCase = es.verdnatura.vnsplits.domain.interactor.scan.DeleteScanUseCase(repository, uiThread, thread)
    }

    @org.junit.Test
    fun deleteScanTest(){
        `when`(repository.deleteScan(scan)).thenReturn(io.reactivex.Observable.empty())
        deleteUseCase.buildUseCaseObservable(es.verdnatura.vnsplits.domain.interactor.scan.DeleteScanUseCase.Params.Companion.forId(scan, scans))
        com.nhaarman.mockito_kotlin.verify(repository).deleteScan(scan)
        com.nhaarman.mockito_kotlin.verifyNoMoreInteractions(repository)
        com.nhaarman.mockito_kotlin.verifyZeroInteractions(uiThread)
        com.nhaarman.mockito_kotlin.verifyZeroInteractions(thread)
    }

    @org.junit.Test
    fun deleteElement(){
        kotlin.test.assertEquals(scans.size, 4)
        scans = deleteUseCase.deleteScans(1, scans as MutableList<es.verdnatura.vnsplits.domain.entity.Scan>)
        kotlin.test.assertEquals(scans.size, 3)
    }

}