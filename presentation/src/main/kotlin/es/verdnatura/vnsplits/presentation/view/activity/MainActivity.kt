package es.verdnatura.vnsplits.presentation.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.HasComponent
import es.verdnatura.vnsplits.presentation.di.components.MainComponent
import es.verdnatura.vnsplits.presentation.di.modules.MainModule
import es.verdnatura.vnsplits.presentation.navigation.MainNavigator
import es.verdnatura.vnsplits.presentation.view.fragment.MainFragment
import java.io.File

/**
 * Created by Enrique Blasco Blanquer on 19/5/17.
 */
class MainActivity: BaseActivity(), HasComponent<MainComponent>, MainNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(R.id.fragmentContainer, MainFragment())
    }

    override fun getSubComponent(): MainComponent {
        return app.component.plus(MainModule(this))
    }

    override fun navigateToScan(type: String) {
        navigator.navigateToScan(this, type)
    }

    override fun navigateToLogin() {
        navigator.navigateToLogin(this)
        finish()
    }

    override fun installApk(file: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}