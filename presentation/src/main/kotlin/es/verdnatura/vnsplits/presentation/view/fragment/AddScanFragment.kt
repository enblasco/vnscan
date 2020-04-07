package es.verdnatura.vnsplits.presentation.view.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import es.verdnatura.core.components.dialogs.retry.DialogRetry
import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase
import es.verdnatura.vnsplits.presentation.di.components.ScanComponent
import es.verdnatura.vnsplits.presentation.presenter.AddScanPresenter
import es.verdnatura.core.adapter.StringAdapter
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.view.dialogs.DialogAddSource
import es.verdnatura.vnsplits.presentation.view.view.AddScanView
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 24/5/17.
 */
class AddScanFragment(val parent: Int, val type: String): ListFragment(), AddScanView {

    @Inject lateinit var presenter: AddScanPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComponent(ScanComponent::class.java).inject(this)
        presenter.setView(this)
        presenter.getWarehouses()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_add_scan, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menuCreateSource -> DialogAddSource({ it -> createSource(it)}).show(fragmentManager, "addSource")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun <T> generateAdapter(elements: List<T>): RecyclerView.Adapter<*> {
        return StringAdapter(elements as List<String>, { item -> onClickWarehouse(item) })
    }

    override fun <T> changeItems(elements: List<T>) {
        (list.adapter as StringAdapter).items = elements as List<String>
    }

    override fun actionErrorList() {
        presenter.getWarehouses()
    }

    override fun onCompleteAddScan() {
        activity.onBackPressed()
    }

    override fun onErrorAddScan(error: String, params: AddScanUseCase.Params) {
        DialogRetry(error, { onClickWarehouse(params.name) }).show(fragmentManager, "dialog")
    }

    override fun onRefresh() {}

    fun onClickWarehouse(name: String){
        presenter.addScan(name, parent, type)
    }

    fun createSource(source: String){
        presenter.createSource(source, (list.adapter as StringAdapter).items)
    }
}