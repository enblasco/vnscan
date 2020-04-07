package es.verdnatura.vnsplits.presentation.view.activity

import android.os.Build
import android.os.Bundle
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.HasComponent
import es.verdnatura.vnsplits.presentation.di.components.LoginComponent
import es.verdnatura.vnsplits.presentation.di.modules.LoginModule
import es.verdnatura.vnsplits.presentation.navigation.UserNavigator
import es.verdnatura.vnsplits.presentation.view.fragment.LoginFragment


/**
 * Created by Enrique Blasco Blanquer on 4/5/17.
 */
class LoginActivity : BaseActivity(), HasComponent<LoginComponent>,UserNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(R.id.fragmentContainer, LoginFragment(R.layout.fragment_login))
    }

    override fun navigate() {
        navigator.navigateToMain(this)
    }

    override fun getSubComponent(): LoginComponent {
        return app.component.plus(LoginModule(this))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment = fragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }
}