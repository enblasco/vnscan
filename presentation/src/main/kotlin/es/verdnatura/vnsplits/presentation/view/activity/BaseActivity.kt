package es.verdnatura.vnsplits.presentation.view.activity

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import es.verdnatura.core.activity.BaseActivity
import es.verdnatura.vnsplits.presentation.AndroidApplication
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.navigation.Navigator


/**
 * Created by Enrique Blasco Blanquer on 20/2/17.
 */
open class BaseActivity : BaseActivity() {

    val app: AndroidApplication get() = application as AndroidApplication
    val navigator: Navigator get() = app.navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    fun changeFragment(containerViewId: Int, fragment: Fragment) {
        val transaccion = fragmentManager.beginTransaction()
        transaccion.replace(containerViewId, fragment)
        transaccion.addToBackStack(null)
        transaccion.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}
