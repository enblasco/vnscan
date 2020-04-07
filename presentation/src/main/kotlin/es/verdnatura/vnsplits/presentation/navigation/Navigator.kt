package es.verdnatura.vnsplits.presentation.navigation

import android.content.Intent
import es.verdnatura.vnsplits.domain.entity.ScanType
import es.verdnatura.vnsplits.presentation.view.activity.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 21/2/17.
 */
@Singleton
class Navigator @Inject constructor(){

    fun navigateToLogin(activity: BaseActivity){
        activity.startActivity(Intent(activity, LoginActivity::class.java))
    }

    fun navigateToMain(activity: BaseActivity){
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    fun navigateToScan(activity: BaseActivity, type: String){
        val intent = Intent(activity, ScanActivity::class.java)
        intent.putExtra(ScanActivity.TYPE, type)
        activity.startActivity(intent)
    }

    fun navigateToRealiceScans(activity: BaseActivity, scan: Int){
        val intent = Intent(activity, ScanLineActivity::class.java)
        intent.putExtra(ScanLineActivity.SCAN, scan)
        activity.startActivity(intent)
    }

}