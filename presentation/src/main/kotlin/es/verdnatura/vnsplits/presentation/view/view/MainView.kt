package es.verdnatura.vnsplits.presentation.view.view

import java.io.File

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
interface MainView: BaseView {

    fun showProgress()
    fun hideProgress()
    fun onNewVersion()
    fun installApk(file: File)
    fun errorUpdate(error: String)

}