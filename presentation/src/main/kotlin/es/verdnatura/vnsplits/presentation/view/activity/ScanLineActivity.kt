package es.verdnatura.vnsplits.presentation.view.activity

import android.os.Bundle
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.HasComponent
import es.verdnatura.vnsplits.presentation.di.components.ScanLineComponent
import es.verdnatura.vnsplits.presentation.di.modules.ScanLineModule
import es.verdnatura.vnsplits.presentation.navigation.ScanLineNavigation
import es.verdnatura.vnsplits.presentation.view.fragment.GroupedScanLineFragment
import es.verdnatura.vnsplits.presentation.view.fragment.ScanLineFragment

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ScanLineActivity: BaseActivity(), HasComponent<ScanLineComponent>, ScanLineNavigation {

    companion object{
        val SCAN = "SCAN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scan = intent.extras.getInt(SCAN)
        addFragment(R.id.fragmentContainer, ScanLineFragment(scan))
    }

    override fun getSubComponent(): ScanLineComponent {
        return app.component.plus(ScanLineModule(this))
    }

    override fun navigateToGroupedLines(lines: List<ScanLine>, total: Int) {
        changeFragment(R.id.fragmentContainer, GroupedScanLineFragment(lines, total))
    }

}