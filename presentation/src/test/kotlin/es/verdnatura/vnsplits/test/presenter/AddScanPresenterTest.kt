package es.verdnatura.vnsplits.test.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase
import es.verdnatura.vnsplits.domain.interactor.scan.GetWarehousesUseCase
import es.verdnatura.vnsplits.presentation.presenter.AddScanPresenter
import es.verdnatura.vnsplits.presentation.view.view.AddScanView
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class AddScanPresenterTest {

    private lateinit var presenter: AddScanPresenter

    val getWarehousesUseCase: GetWarehousesUseCase = mock()
    val addScanUseCase: AddScanUseCase = mock()
    val view: AddScanView = mock()

    val error = "Error"
    val scan = 1
    val name = "Scan 1"
    val type = "BUYS"
    val params = AddScanUseCase.Params.forAddScan(type, scan, name)

    @Before
    fun setUp(){
        presenter = AddScanPresenter(getWarehousesUseCase, addScanUseCase)
        presenter.setView(view)
    }

    @Test
    fun testOnFinishAddScan(){
        presenter.onFinishAddScan()
        verify(view).onCompleteAddScan()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnErrorCreateScan(){
        presenter.onErrorCreateScan(error, params)
        verify(view).onErrorAddScan(error, params)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testGetWarehouses(){
        presenter.getWarehouses()
        verify(view).showProgress()
        verify(getWarehousesUseCase).execute(any<DisposableObserver<List<String>>>(), any<() -> Unit>())
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(getWarehousesUseCase)
    }

    @Test
    fun testOnErrorGetWarehouses(){
        presenter.onErrorGetWarehouses(error)
        verify(view).showErrorList(error)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnCompletGetWarehouses(){
        presenter.hideProgress()
        verify(view).hideProgress()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testOnFinishGetWarehouses(){
        presenter.onFinishGetWarehouses(listOf(""))
        verify(view).createAdapter(listOf(""))
        verifyNoMoreInteractions(view)
    }

    @Test
    fun testAddScan(){
        presenter.addScan(name, scan, type)
        verify(view).showProgress()
        verify(addScanUseCase).execute(any<DisposableObserver<Int>>(), any<AddScanUseCase.Params>())
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(addScanUseCase)
    }

}