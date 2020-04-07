package es.verdnatura.vnsplits.presentation.di.components

import android.app.Application
import android.content.SharedPreferences
import dagger.Component
import es.verdnatura.core.database.VnOpenHelper
import es.verdnatura.vnsplits.data.database.ScanLineDatabase
import es.verdnatura.vnsplits.data.preferences.Preferences
import es.verdnatura.vnsplits.data.rest.NsConnector
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import es.verdnatura.vnsplits.presentation.AndroidApplication
import es.verdnatura.vnsplits.presentation.di.modules.*
import io.reactivex.Scheduler

import javax.inject.Named

import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 20/2/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @Named("ui_thread") fun uiThread(): Scheduler
    @Named("executor_thread") fun executorThread(): Scheduler

    fun application(): Application
    fun sharedPreferences(): SharedPreferences
    fun preferences(): Preferences
    fun nsConnector(): NsConnector

    fun securityRepository(): SecurityRepository
    fun scanRepository(): ScanRepository
    fun scanLineRepositoryRest(): ScanLineRepository

    fun vnOpenHelper(): VnOpenHelper
    fun scanLineDatabase(): ScanLineDatabase

    fun inject(application: AndroidApplication)

    fun plus(loginModule: LoginModule): LoginComponent
    fun plus(mainModule: MainModule): MainComponent
    fun plus(scanModule: ScanModule): ScanComponent
    fun plus(scanLineModule: ScanLineModule): ScanLineComponent
}