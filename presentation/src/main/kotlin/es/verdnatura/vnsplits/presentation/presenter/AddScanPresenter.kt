package es.verdnatura.vnsplits.presentation.presenter

import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase
import es.verdnatura.vnsplits.domain.interactor.scan.CreateSourceUseCase
import es.verdnatura.vnsplits.domain.interactor.scan.GetWarehousesUseCase
import es.verdnatura.vnsplits.presentation.observer.AddScanObserver
import es.verdnatura.vnsplits.presentation.view.view.AddScanView
import es.verdnatura.vnsplits.presentation.view.view.BaseView
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


/**
 * Created by Enrique Blasco Blanquer on 5/6/17.
 */
class AddScanPresenter: BasePresenter, AddScanFunctions {

    private lateinit var view: AddScanView
    private val getWarehousesUseCase: GetWarehousesUseCase
    private val addScanUseCase: AddScanUseCase
    private val creteSourceUseCase: CreateSourceUseCase

    @Inject
    constructor(getWarehousesUseCase: GetWarehousesUseCase, addScanUseCase: AddScanUseCase, createSourceUseCase: CreateSourceUseCase){
        this.getWarehousesUseCase = getWarehousesUseCase
        this.addScanUseCase = addScanUseCase
        this.creteSourceUseCase = createSourceUseCase
    }

    override fun onDestroy() {}

    override fun setView(v: BaseView) {
        view = v as AddScanView
    }

    override fun onFinishAddScan(scan: Int) {
        view.onCompleteAddScan()
    }

    override fun onErrorCreateScan(message: String, params: AddScanUseCase.Params) {
        view.onErrorAddScan(message, params)
    }

    fun getWarehouses(){
        view.showProgress()
        getWarehousesUseCase.execute(GetWarehousesObserver(this), {})
    }

    fun showError(error: String) {
        view.hideProgress()
        view.showErrorList(error)
    }

    fun hideProgress(){
        view.hideProgress()
    }

    fun onFinishGetWarehouses(warehouses: List<String>){
        view.createAdapter(warehouses)
    }

    fun notifyChangedSources(sources: List<String>){
        view.notifyDataHasChanged(sources)
    }

    fun addScan(name: String, parent: Int = 0, type: String){
        view.showProgress()
        val params = AddScanUseCase.Params.forAddScan(type, parent, name)
        addScanUseCase.execute(AddScanObserver(this, params), params)
    }

    fun createSource(source: String, sources: List<String>){
        view.showProgress()
        creteSourceUseCase.execute(CreateSourceObserver(this), CreateSourceUseCase.Params.forCreate(source, sources as MutableList<String>))
    }

    class GetWarehousesObserver(val presenter: AddScanPresenter) : DisposableObserver<List<String>>(){
        override fun onComplete() {
            presenter.hideProgress()
        }

        override fun onNext(t: List<String>?) {
            presenter.onFinishGetWarehouses(t!!)
        }

        override fun onError(e: Throwable?) {
            presenter. showError(e?.message.toString())
        }

    }

    class CreateSourceObserver(val presenter: AddScanPresenter): DisposableObserver<List<String>>() {
        override fun onError(e: Throwable?) {
            presenter.showError(e?.message.toString())
        }

        override fun onComplete() {
            presenter.hideProgress()
        }

        override fun onNext(t: List<String>?) {
            presenter.notifyChangedSources(t!!)
        }

    }
}