package es.verdnatura.vnsplits.presentation.navigation

import java.io.File

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
interface MainNavigator {

    fun navigateToScan(type: String)
    fun navigateToLogin()
    fun installApk(file: File)

}