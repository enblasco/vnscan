package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.entity.Scan

/**
 * Created by Enrique Blasco Blanquer on 2/6/17.
 */
class ShowHideChildUseCaseTest : ScanBaseTest() {

    private lateinit var useCaseShow: es.verdnatura.vnsplits.domain.interactor.scan.ShowChildsUseCase
    private lateinit var useCaseHide: es.verdnatura.vnsplits.domain.interactor.scan.HideChildsUseCase
    val index = 1

    @org.junit.Before
    fun setUp(){
        useCaseShow = es.verdnatura.vnsplits.domain.interactor.scan.ShowChildsUseCase(uiThread, thread)
        useCaseHide = es.verdnatura.vnsplits.domain.interactor.scan.HideChildsUseCase(uiThread, thread)
    }

    @org.junit.Test
    fun testShowChilds(){
        kotlin.test.assertEquals(scans.size, 4)
        useCaseShow.showChilds(es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds.Companion.forShowChilds(scans as MutableList<Scan>, scans.get(index)))
        kotlin.test.assertEquals(scans.size, 8)

    }

    @org.junit.Test
    fun testHideChilds(){
        useCaseShow.showChilds(es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds.Companion.forShowChilds(scans as MutableList<Scan>, scans.get(index)))
        kotlin.test.assertEquals(scans.size, 8)
        scans = useCaseHide.hideScans(scans as MutableList<es.verdnatura.vnsplits.domain.entity.Scan>, scans.get(index))
        kotlin.test.assertEquals(scans.size, 4)
    }

}