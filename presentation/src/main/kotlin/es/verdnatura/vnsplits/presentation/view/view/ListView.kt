package es.verdnatura.vnsplits.presentation.view.view

/**
 * Created by Enrique Blasco Blanquer on 5/6/17.
 */
interface ListView: BaseView {

    fun showProgress()
    fun hideProgress()
    fun <T>createAdapter(scans: List<T>)
    fun showErrorList(error: String)
    fun <T>notifyDataHasChanged(elements: List<T>)

}