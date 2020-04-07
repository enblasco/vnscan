package es.verdnatura.vnsplits.presentation.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import es.verdnatura.core.database.VnOpenHelper
import es.verdnatura.vnsplits.data.database.ScanLineDatabase
import es.verdnatura.vnsplits.data.preferences.Preferences
import es.verdnatura.vnsplits.data.rest.NsConnector
import es.verdnatura.vnsplits.data.rest.ScanApiImpl
import es.verdnatura.vnsplits.data.rest.ScanLineApiImpl
import es.verdnatura.vnsplits.data.rest.SecurityApiImpl
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import es.verdnatura.vnsplits.domain.repository.SecurityRepository
import javax.inject.Named
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Enrique Blasco Blanquer on 20/2/17.
 */
@Module
class ApplicationModule(private val application: Application) {

    private val PREF_NAME = "prefs.xml"

    @Provides @Named("executor_thread") fun provideExecutorThread() = Schedulers.newThread()

    @Provides @Named("ui_thread") fun provideUiThread() =  AndroidSchedulers.mainThread()

    @Provides @Singleton fun provideApplication() = application

    @Provides @Singleton fun providesNsConnector() = NsConnector(application)

    @Provides @Singleton fun provideSecurityRepository(securityRepository: SecurityApiImpl): SecurityRepository =  securityRepository

    @Provides @Singleton fun provideScanRepository(scanRepository: ScanApiImpl): ScanRepository =  scanRepository

    @Provides @Singleton fun provideScanLineRepository(scanLineRepository: ScanLineApiImpl): ScanLineRepository = scanLineRepository

    @Provides @Singleton fun provideScanLineDatabase(vnOpenHelper: VnOpenHelper): ScanLineDatabase = ScanLineDatabase(vnOpenHelper)

    @Provides @Singleton fun getAppPreferences(): SharedPreferences = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    @Provides @Singleton fun getVnOpenHelper(): VnOpenHelper = VnOpenHelper(application)

    @Provides @Singleton fun getPreferences(sp: SharedPreferences): Preferences = Preferences(sp)
}