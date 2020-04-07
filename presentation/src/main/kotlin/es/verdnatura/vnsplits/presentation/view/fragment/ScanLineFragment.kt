package es.verdnatura.vnsplits.presentation.view.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.*
import es.verdnatura.core.components.dialogs.retry.DialogRetry
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.components.ScanLineComponent
import es.verdnatura.vnsplits.presentation.navigation.ScanLineNavigation
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import es.verdnatura.vnsplits.presentation.view.adapter.ScanLineAdapter
import es.verdnatura.vnsplits.presentation.view.dialogs.DialogDeleteScanLines
import es.verdnatura.vnsplits.presentation.view.view.ScanLineView
import kotlinx.android.synthetic.main.fragment_scan_line.*
import javax.inject.Inject
import android.view.ViewGroup



/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ScanLineFragment(val scan: Int): ListFragment(), ScanLineView {

    @Inject lateinit var scanLinePresenter: ScanLinePresenter
    lateinit var scanLineNavigation: ScanLineNavigation

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is ScanLineNavigation)
            scanLineNavigation = activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.fragment_scan_line, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComponent(ScanLineComponent::class.java).inject(this)
        scanLinePresenter.setView(this)
        if(scanLinePresenter.lines == null)
            scanLinePresenter.listScanLine(scan)
        edtScan.onScan {
            code -> scanLinePresenter.insertScanLine(scan, code)
            edtScan.setTextScan("")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_scan_line, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menuUploadLines -> uploadScanLines()
            R.id.menuDelete -> DialogDeleteScanLines(activity, { deleteScanLines() }).show()
            R.id.menuGroup -> scanLinePresenter.groupLines()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun <T> generateAdapter(elements: List<T>): RecyclerView.Adapter<*> {
        return ScanLineAdapter(elements as MutableList<ScanLine>, { it -> it.Delete = !it.Delete })
    }

    override fun <T> changeItems(elements: List<T>) {
        (list.adapter as ScanLineAdapter).items = elements as MutableList<ScanLine>
    }

    override fun actionErrorList() {
        scanLinePresenter.listScanLine(scan)
    }

    override fun addScanLine(scanLine: ScanLine) {
        var items = (list.adapter as ScanLineAdapter).items
        items.add(scanLine)
        notifyDataHasChanged(items)
    }

    override fun onErrorCreateScanLine(error: String) {
        DialogRetry(error, {uploadScanLines()}).show(fragmentManager, "dialog")
    }

    override fun onErrorDeleteScanLine(error: String) {
        DialogRetry(error, {deleteScanLines()}).show(fragmentManager, "dialog")
    }

    override fun onRefresh() {
        scanLinePresenter.downloadLines(scan)
    }

    fun uploadScanLines(){
        scanLinePresenter.createLines(scan)
    }

    fun deleteScanLines(){
        scanLinePresenter.deleteLines()
    }

    override fun showLinesGrouped(lines: List<ScanLine>) {
        val total = (list.adapter as ScanLineAdapter).items.size
        scanLineNavigation.navigateToGroupedLines(lines, total)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        edtScan.stopFocusThread()
    }
}