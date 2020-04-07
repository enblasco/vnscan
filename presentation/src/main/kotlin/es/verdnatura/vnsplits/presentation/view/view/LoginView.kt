package es.verdnatura.vnsplits.presentation.view.view

/**
 * Created by Enrique Blasco Blanquer on 10/5/17.
 */
interface LoginView: BaseView {

    fun navigateToMain()
    fun showProgress()
    fun hideProgress()
    fun showError(error: String)
    fun saveUser()
}