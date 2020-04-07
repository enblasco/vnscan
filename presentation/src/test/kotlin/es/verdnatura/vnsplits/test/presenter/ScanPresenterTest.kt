package es.verdnatura.vnsplits.test.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.interactor.scan.*
import es.verdnatura.vnsplits.domain.params.ParamsScans
import es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import es.verdnatura.vnsplits.presentation.view.view.ScanView
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 2/6/17.
 */
class ScanPresenterTest {

    lateinit var presenter: ScanPresenter

    val scanListUseCase: ScanListUseCase = mock()
    val showChildsUseCase: ShowChildsUseCase = mock()
    val hideChildsUseCase: HideChildsUseCase = mock()
    val addScanUseCase: AddScanUseCase = mock()
    val deleteScanUseCase: DeleteScanUseCase = mock()
    val scanView: ScanView = mock()

    val error = "Error"
    val scan = 1
    val name = "Scan 1"
    val params = AddScanUseCase.Params.forAddScan("BUYS", scan, name)

    @Before
    fun setUp(){
        presenter = ScanPresenter(scanListUseCase, showChildsUseCase, hideChildsUseCase, addScanUseCase, deleteScanUseCase )
        presenter.setView(scanView)
        presenter.type = "BUYS"
    }

    @Test
    fun listScansTest(){
        presenter.listScans()
        verify(scanView).showProgress()
        verify(scanListUseCase).execute(any<DisposableObserver<List<Scan>>>(), any<ParamsScans>())
    }

    @Test
    fun onFinishRepositoryTest(){
        presenter.onFinishRepository()
        verify(scanView).hideProgress()
    }

    @Test
    fun onErrorListScansTest(){
        presenter.onErrorListScans(error)
        verify(scanView).showErrorList(error)
        verify(scanView).hideProgress()
    }

    @Test
    fun onErrorDeleteScanTest(){
        presenter.onErrorDeleteScan(error, scan)
        verify(scanView).showErrorDeleteScan(error, scan)
        verify(scanView).hideProgress()
    }

    @Test
    fun onErrorCreateScanTest(){
        presenter.onErrorCreateScan(error, params)
        verify(scanView).showErrorAddChild(error, params)
        verify(scanView).hideProgress()
    }

    @Test
    fun addScanTest(){
        presenter.addScan(scan, name)
        verify(scanView).showProgress()
        verify(addScanUseCase).execute(any<DisposableObserver<Int>>(), any<AddScanUseCase.Params>())
    }

    @Test
    fun deleteScanTest(){
        presenter.deleteScan(scan, mutableListOf<Scan>())
        verify(scanView).showProgress()
        verify(deleteScanUseCase).execute(any<DisposableObserver<List<Scan>>>(), any<DeleteScanUseCase.Params>())
    }

    @Test
    fun onAddScanTest(){
        presenter.onAddScan(scan)
        verify(scanView).scan(scan)
    }

    @Test
    fun createAdapterTest(){
        presenter.createAdapter(listOf<Scan>())
        verify(scanView).createAdapter(listOf<Scan>())
    }

    @Test
    fun showChildsTest(){
        presenter.showChilds(listOf<Scan>(), Scan())
        verify(showChildsUseCase).execute(any<DisposableObserver<List<Scan>>>(), any<ParamsShowHideChilds>())
    }

    @Test
    fun hideChildsTest(){
        presenter.hideChilds(listOf<Scan>(), Scan())
        verify(hideChildsUseCase).execute(any<DisposableObserver<List<Scan>>>(), any<ParamsShowHideChilds>())
    }

    @Test
    fun notifyDataHasChangedTest(){
        presenter.notifyDataHasChanged(listOf<Scan>())
        verify(scanView).notifyDataHasChanged(listOf<Scan>())
    }
}