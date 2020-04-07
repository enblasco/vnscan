package es.verdnatura.vnsplits.presentation

import android.app.Application
import es.verdnatura.vnsplits.data.preferences.Preferences
import es.verdnatura.vnsplits.presentation.di.components.ApplicationComponent
import es.verdnatura.vnsplits.presentation.di.components.DaggerApplicationComponent
import es.verdnatura.vnsplits.presentation.di.modules.ApplicationModule
import es.verdnatura.vnsplits.presentation.navigation.Navigator
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 20/2/17.
 */

class AndroidApplication: Application() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var preferences: Preferences

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        //initializeLeakDetection()
    }

    /*private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }*/
}