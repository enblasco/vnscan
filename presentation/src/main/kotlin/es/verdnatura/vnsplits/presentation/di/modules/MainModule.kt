package es.verdnatura.vnsplits.presentation.di.modules

import dagger.Module
import dagger.Provides
import es.verdnatura.vnsplits.domain.interactor.scan.GetVersionUseCase
import es.verdnatura.vnsplits.domain.interactor.scan.UpdateUseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.view.activity.MainActivity
import io.reactivex.Scheduler
import javax.inject.Named


/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
@Module
class MainModule(private val activity: MainActivity){

    @Provides
    @PerActivity
    fun providesGetVersionUseCase(scanRepository: ScanRepository,
                        @Named("ui_thread") uiThread: Scheduler,
                        @Named("executor_thread") executorThread: Scheduler): GetVersionUseCase {
        return GetVersionUseCase(scanRepository, uiThread, executorThread)
    }

    @Provides
    @PerActivity
    fun providesUpdateUseCase(scanRepository: ScanRepository,
                                  @Named("ui_thread") uiThread: Scheduler,
                                  @Named("executor_thread") executorThread: Scheduler): UpdateUseCase {
        return UpdateUseCase(scanRepository, uiThread, executorThread)
    }


}