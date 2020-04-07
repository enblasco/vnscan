package es.verdnatura.vnsplits.presentation.presenter

import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.interactor.scan.*
import es.verdnatura.vnsplits.domain.params.ParamsScans
import es.verdnatura.vnsplits.domain.params.ParamsShowHideChilds
import es.verdnatura.vnsplits.presentation.observer.AddScanObserver
import es.verdnatura.vnsplits.presentation.observer.DeleteScanObserver
import es.verdnatura.vnsplits.presentation.observer.ListScansObserver
import es.verdnatura.vnsplits.presentation.observer.ShowHideChildsObserver
import es.verdnatura.vnsplits.presentation.view.view.BaseView
import es.verdnatura.vnsplits.presentation.view.view.ScanView
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
class ScanPresenter : BasePresenter, AddScanFunctions {

    private val scanListUseCase: ScanListUseCase
    private val showChildsUseCase: ShowChildsUseCase
    private val hideChildsUseCase: HideChildsUseCase
    private val addScanUseCase: AddScanUseCase
    private val deleteScanUseCase: DeleteScanUseCase

    lateinit var type: String
    lateinit var view: ScanView

    @Inject
    constructor(scanListUseCase: ScanListUseCase, showChildsUseCase: ShowChildsUseCase, hideChildsUseCase: HideChildsUseCase,
                addScanUseCase: AddScanUseCase, deleteScanUseCase: DeleteScanUseCase){
        this.scanListUseCase = scanListUseCase
        this.showChildsUseCase = showChildsUseCase
        this.hideChildsUseCase = hideChildsUseCase
        this.addScanUseCase = addScanUseCase
        this.deleteScanUseCase = deleteScanUseCase
    }

    override fun onDestroy() {}

    override fun setView(v: BaseView) { this.view = v as ScanView }

    fun listScans() {
        view.showProgress()
        dowloadScans()
    }

    fun dowloadScans(){
        scanListUseCase.execute(ListScansObserver(this), ParamsScans.forScan(type))
    }

    fun onFinishRepository() = view.hideProgress()

    fun onErrorListScans(error: String) {
        view.hideProgress()
        view.showErrorList(error)
    }

    fun onErrorDeleteScan(error: String, id:Int) {
        view.showErrorDeleteScan(error, id)
        view.hideProgress()
    }

    override fun onFinishAddScan(scan: Int) {
        onFinishRepository()
        view.scan(scan)
    }

    override fun onErrorCreateScan(error: String, params: AddScanUseCase.Params){
        view.showErrorAddChild(error, params)
        view.hideProgress()
    }

    fun addScan(parent: Int = 0, name: String) {
        view.showProgress()
        val params =  AddScanUseCase.Params.forAddScan(type, parent, name)
        addScanUseCase.execute(AddScanObserver(this, params), params)
    }

    fun deleteScan(id: Int, scans: List<Scan>) {
        view.showProgress()
        deleteScanUseCase.execute(DeleteScanObserver(this, id), DeleteScanUseCase.Params.forId(id, scans))
    }

    fun onAddScan(id: Int) = view.scan(id)

    fun createAdapter(scans: List<Scan>) = view.createAdapter(scans)

    fun showChilds(scans: List<Scan>,  scan: Scan) = showChildsUseCase.execute(ShowHideChildsObserver(this),  ParamsShowHideChilds.forShowChilds(scans.toMutableList(), scan))

    fun hideChilds(scans: List<Scan>, scan: Scan) = hideChildsUseCase.execute(ShowHideChildsObserver(this), ParamsShowHideChilds.forShowChilds(scans.toMutableList(), scan))

    fun notifyDataHasChanged(scans: List<Scan>) = view.notifyDataHasChanged(scans)

}