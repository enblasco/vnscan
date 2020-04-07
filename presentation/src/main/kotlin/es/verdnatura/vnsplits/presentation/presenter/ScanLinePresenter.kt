package es.verdnatura.vnsplits.presentation.presenter

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.domain.interactor.scanline.*
import es.verdnatura.vnsplits.presentation.observer.*
import es.verdnatura.vnsplits.presentation.view.view.BaseView
import es.verdnatura.vnsplits.presentation.view.view.ScanLineView
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ScanLinePresenter: BasePresenter {

    var lines: List<ScanLine>? = null

    private val listScanLineUseCase: ListScanLineUseCase
    private val insertScanLineUseCase: InsertScanLineUseCase
    private val createScanLineUseCase: CreateLinesUseCase
    private val deleteScanLineUseCase: DeleteScanLineUseCase
    private val groupLinesUseCase: GroupLinesUseCase

    private lateinit var view: ScanLineView

    @Inject
    constructor(listScanLineUseCase: ListScanLineUseCase, insertScanLineUseCase: InsertScanLineUseCase,
                createScanLineUseCase: CreateLinesUseCase, deleteScanLineUseCase: DeleteScanLineUseCase,
                groupLinesUseCase: GroupLinesUseCase){
        this.listScanLineUseCase = listScanLineUseCase
        this.insertScanLineUseCase = insertScanLineUseCase
        this.createScanLineUseCase = createScanLineUseCase
        this.deleteScanLineUseCase = deleteScanLineUseCase
        this.groupLinesUseCase = groupLinesUseCase
    }

    override fun onDestroy() {}

    override fun setView(v: BaseView) {
        view = v as ScanLineView
    }

    fun listScanLine(scan: Int){
        view.showProgress()
        downloadLines(scan)
    }

    fun downloadLines(scan: Int){
        listScanLineUseCase.execute(ListScanLineObserver(this), ListScanLineUseCase.Params.forGetScanLines(scan))
    }

    fun insertScanLine(scan: Int, code: String){
        insertScanLineUseCase.execute(InsertScanLineObserver(this), InsertScanLineUseCase.Params.forInsertScan(scan, code))
    }

    fun onListScanLine(lines: List<ScanLine>){
        this.lines = lines
        view.createAdapter(lines)
    }

    fun onErrorListScanLine(error: String){
        view.hideProgress()
        view.showErrorList(error)
    }

    fun hideProgress() {
        view.hideProgress()
    }

    fun addScanLine(scanLine: ScanLine){
        view.addScanLine(scanLine)
    }

    fun createLines(scan: Int){
        view.showProgress()
        createScanLineUseCase.execute(CreateScanLineObserver(this), CreateLinesUseCase.Params.forCreate(scan, lines!!))
    }

    fun onChangeLines(lines: List<ScanLine>){
        this.lines = lines
        view.notifyDataHasChanged(lines)
    }

    fun onErrorCreateLines(error: String){
        view.onErrorCreateScanLine(error)
        view.hideProgress()
    }

    fun deleteLines(){
        view.showProgress()
        deleteScanLineUseCase.execute(DeleteScanLineObserver(this), DeleteScanLineUseCase.Params.forDeleteLines(lines!!))
    }

    fun onErrorDeleteLines(error: String){
        view.onErrorDeleteScanLine(error)
        view.hideProgress()
    }

    fun groupLines(){
        groupLinesUseCase.execute(GroupLinesObserver(this), GroupLinesUseCase.Params.forGroupLines(lines!!))
    }

    fun showLinesGrouped(lines: List<ScanLine>){
        view.showLinesGrouped(lines)
    }
}