package es.verdnatura.vnsplits.test.view.activity

import android.support.test.runner.AndroidJUnit4
import es.verdnatura.core.adapter.MenuAdapter
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.view.activity.MainActivity
import es.verdnatura.vnsplits.test.framework.AcceptanceTest

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest: AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun testOnCreate(){
        checkThat.viewExist(R.id.mainListItems)
    }

    @Test
    fun clickItem(){
        events.clickItemRecyclerView<MenuAdapter.ViewHolder>(R.id.mainListItems, 1)
    }

}