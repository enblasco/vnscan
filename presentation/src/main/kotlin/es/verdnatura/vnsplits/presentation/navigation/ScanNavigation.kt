package es.verdnatura.vnsplits.presentation.navigation

/**
 * Created by Enrique Blasco Blanquer on 24/5/17.
 */
interface ScanNavigation {

    fun addScan(type: String, id: Int = 0)
    fun startScan(id: Int)

}