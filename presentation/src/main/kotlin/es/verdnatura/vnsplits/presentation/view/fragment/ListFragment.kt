package es.verdnatura.vnsplits.presentation.view.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import es.verdnatura.core.components.dialogs.retry.DialogRetry
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.view.view.ListView
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.progress.*


/**
 * Created by Enrique Blasco Blanquer on 7/6/17.
 */
abstract class ListFragment : BaseFragment(R.layout.fragment_list), ListView {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeRefresh()
    }

    override fun showProgress() = vnProgress.show()

    override fun hideProgress() {
        vnProgress.hide()
        swipeList.hideProgress()
    }

    override fun <T>createAdapter(elements: List<T>) {
        list.setAdapterList(generateAdapter(elements))
        enableVnSwipeRefresh()
    }

    override fun showErrorList(error: String) { showDialogRetry(error, { actionErrorList() }) }

    override fun <T>notifyDataHasChanged(elements: List<T>) {
        changeItems(elements)
        list.adapter.notifyDataSetChanged()
    }

    fun showDialogRetry(error: String, retry: () -> Unit){
        DialogRetry(error, { retry() }).show(fragmentManager, "dialogFragment")
    }

    fun enableVnSwipeRefresh() = swipeList.enableProgress()

    fun initSwipeRefresh() {
        swipeList.setOnRefreshListener { onRefreshList() }
        swipeList.disableProgress()
    }

    fun onRefreshList(){
        swipeList.showProgress()
        onRefresh()
    }

    abstract fun <T> generateAdapter(elements: List<T>): RecyclerView.Adapter<*>

    abstract fun <T> changeItems(elements: List<T>)

    abstract fun actionErrorList()

    abstract fun onRefresh()
}