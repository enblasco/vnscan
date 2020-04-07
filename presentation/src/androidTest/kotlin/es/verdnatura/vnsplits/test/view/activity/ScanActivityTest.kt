package es.verdnatura.vnsplits.test.view.activity

import android.content.Intent
import android.support.test.runner.AndroidJUnit4
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.view.activity.ScanActivity
import es.verdnatura.vnsplits.test.framework.AcceptanceTest
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu

/**
 * Created by Enrique Blasco Blanquer on 29/5/17.
 */
@RunWith(AndroidJUnit4::class)
class ScanActivityTest: AcceptanceTest<ScanActivity>(ScanActivity::class.java, false) {

    val recyclerList = R.id.list

    @Test
    fun testContainsRecyclerList(){
        testRule.launchActivity(Intent().putExtra(ScanActivity.TYPE, "AIRPORT"))
        checkThat.viewExist(recyclerList)
    }

    @Test
    fun testMenuAdd() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext())
        events.clickOnView(R.id.menuAddScan)
    }

}