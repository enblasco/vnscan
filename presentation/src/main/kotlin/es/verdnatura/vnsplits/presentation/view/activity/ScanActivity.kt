package es.verdnatura.vnsplits.presentation.view.activity

import android.os.Bundle
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.HasComponent
import es.verdnatura.vnsplits.presentation.di.components.ScanComponent
import es.verdnatura.vnsplits.presentation.di.modules.ScanModule
import es.verdnatura.vnsplits.presentation.navigation.ScanNavigation
import es.verdnatura.vnsplits.presentation.view.fragment.AddScanFragment
import es.verdnatura.vnsplits.presentation.view.fragment.ScanFragment

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
class ScanActivity : BaseActivity(), HasComponent<ScanComponent>, ScanNavigation {

    companion object{
        val TYPE = "TYPE"
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = intent.getStringExtra(ScanActivity.TYPE)
        val fragmentScan = ScanFragment(type)
        addFragment(R.id.fragmentContainer, fragmentScan)
    }

    override fun getSubComponent(): ScanComponent {
        return app.component.plus(ScanModule(this))
    }

    override fun addScan(type: String, id: Int) {
        newScan(id, type)
    }

    override fun startScan(id: Int) {
        navigator.navigateToRealiceScans(this, id)
    }

    private fun newScan(scan: Int, type: String){
        changeFragment(R.id.fragmentContainer, AddScanFragment(scan, type))
    }
}