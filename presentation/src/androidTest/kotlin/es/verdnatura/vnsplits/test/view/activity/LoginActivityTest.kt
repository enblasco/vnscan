package es.verdnatura.vnsplits.test.view.activity

import android.support.test.runner.AndroidJUnit4
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.view.activity.LoginActivity
import es.verdnatura.vnsplits.test.framework.AcceptanceTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Enrique Blasco Blanquer on 12/5/17.
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest: AcceptanceTest<LoginActivity>(LoginActivity::class.java) {

    @Test
    fun putUserName(){
        val editId = R.id.edtUser
        events.typeTextOnView(editId, "user")
        checkThat.viewContainsText(editId, "user")
    }

    @Test
    fun showPasswordTest(){
        val editId = R.id.edtPassword
        events.typeTextOnView(editId, "hola")
        checkThat.viewContainsText(editId, "hola")
        events.clickOnView(editId)
    }

}