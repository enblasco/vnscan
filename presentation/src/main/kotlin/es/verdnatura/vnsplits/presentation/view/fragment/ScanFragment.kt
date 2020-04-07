package es.verdnatura.vnsplits.presentation.view.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import es.verdnatura.core.UtilsResources
import es.verdnatura.core.components.dialogs.options.DialogOptions
import es.verdnatura.core.components.dialogs.options.DialogOptionsItem
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.components.ScanComponent
import es.verdnatura.vnsplits.presentation.navigation.ScanNavigation
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import es.verdnatura.vnsplits.presentation.view.adapter.ScansAdapter
import es.verdnatura.vnsplits.presentation.view.dialogs.DialogAddChild
import es.verdnatura.vnsplits.presentation.view.dialogs.DialogDeleteScan
import es.verdnatura.vnsplits.presentation.view.view.ScanView
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject


/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
class ScanFragment(val type: String): ListFragment(), ScanView {

    @Inject lateinit var scanPresenter: ScanPresenter
    lateinit var scanNavigation: ScanNavigation

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if(activity is ScanNavigation)
            scanNavigation = activity
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.getComponent(ScanComponent::class.java).inject(this)
        scanPresenter.setView(this)
        scanPresenter.type = type
    }

    override fun onResume() {
        super.onResume()
        scanPresenter.listScans()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_scan, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menuAddScan -> scanNavigation.addScan(type)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showErrorAddChild(error: String, params: AddScanUseCase.Params) { showDialogRetry(error, { scanPresenter.addScan(params.parent, params.name)} ) }

    override fun showErrorDeleteScan(error: String, id: Int) { showDialogRetry(error, { deleteScan(id) })}

    override fun scan(id: Int) = scanNavigation.startScan(id)

    override fun onRefresh() {
        scanPresenter.dowloadScans()
    }

    fun onShowMore(scan: Scan){
        if(!scan.Displayed) scanPresenter.showChilds((list.adapter as ScansAdapter).items, scan)
        else scanPresenter.hideChilds((list.adapter as ScansAdapter).items, scan)
    }

    fun onClickItemAdapter(id: Int){
        DialogOptions(activity, getMenuOptions(id)).show()
    }

    fun getMenuOptions(id: Int): List<DialogOptionsItem>{
        return listOf(
                DialogOptionsItem(UtilsResources.getResourceString(activity, R.string.scan), { scan(id) }),
                DialogOptionsItem(UtilsResources.getResourceString(activity, R.string.add_child), { scanNavigation.addScan(type, id) }),
                DialogOptionsItem(UtilsResources.getResourceString(activity, R.string.add_child_scan), { showDialogAddChild(id) }),
                DialogOptionsItem(UtilsResources.getResourceString(activity, R.string.delete_scan), { showDialogDelete(id) })
        )
    }

    fun showDialogDelete(id: Int){
        DialogDeleteScan(activity, { deleteScan(id) }).show()
    }

    fun deleteScan(id: Int){
        scanPresenter.deleteScan(id, (list.adapter as ScansAdapter).items)
    }

    fun showDialogAddChild(parent: Int){
        DialogAddChild(parent, {parent, name -> addChildAndScan(parent, name)}).show(fragmentManager, "dialgoAddChild")
    }

    fun addChildAndScan(parent: Int, name: String){
        scanPresenter.addScan(parent, name)
    }

    override fun <T> generateAdapter(elements: List<T>): RecyclerView.Adapter<*> {
        return ScansAdapter(elements as MutableList<Scan>, { onClickItemAdapter(it.Id) }, { onShowMore(it) })
    }

    override fun <T> changeItems(elements: List<T>) {
        (list.adapter as ScansAdapter).items = elements as MutableList<Scan>
    }

    override fun actionErrorList() {
        scanPresenter.listScans()
    }
}
