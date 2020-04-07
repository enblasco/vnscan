package es.verdnatura.vnsplits.presentation.di.modules

import dagger.Module
import dagger.Provides
import es.verdnatura.vnsplits.domain.interactor.scan.*
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.presenter.AddScanPresenter
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import es.verdnatura.vnsplits.presentation.view.activity.ScanActivity
import io.reactivex.Scheduler
import javax.inject.Named

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
@Module
open class ScanModule(private val activity: ScanActivity) {

    @PerActivity
    @Provides
    fun providesScanPresenter(scanListUseCase: ScanListUseCase,
                              showChildsUseCase: ShowChildsUseCase,
                              hideChildsUseCase: HideChildsUseCase,
                              addScanUseCase: AddScanUseCase,
                              deleteScanUseCase: DeleteScanUseCase):
            ScanPresenter = ScanPresenter(scanListUseCase, showChildsUseCase, hideChildsUseCase, addScanUseCase, deleteScanUseCase)

    @Provides
    @PerActivity
    fun providesScanListUseCase(scanRepository: ScanRepository,
                                @Named("ui_thread") uiThread: Scheduler,
                                @Named("executor_thread") executorThread: Scheduler): ScanListUseCase = ScanListUseCase(scanRepository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun providesShowChildsUseCase(@Named("ui_thread") uiThread: Scheduler,
                                  @Named("executor_thread") executorThread: Scheduler): ShowChildsUseCase = ShowChildsUseCase(uiThread, executorThread)

    @Provides
    @PerActivity
    fun providesHideChildsUseCase(@Named("ui_thread") uiThread: Scheduler,
                                  @Named("executor_thread") executorThread: Scheduler): HideChildsUseCase = HideChildsUseCase(uiThread, executorThread)

    @Provides
    @PerActivity
    fun providesAddScanUseCase(scanRepository: ScanRepository,
                               @Named("ui_thread") uiThread: Scheduler,
                               @Named("executor_thread") executorThread: Scheduler): AddScanUseCase = AddScanUseCase(scanRepository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun providesDeleteScanUseCase(scanRepository: ScanRepository,
                               @Named("ui_thread") uiThread: Scheduler,
                               @Named("executor_thread") executorThread: Scheduler): DeleteScanUseCase = DeleteScanUseCase(scanRepository, uiThread, executorThread)


    @Provides
    @PerActivity
    fun providesGetWarehousesUSeCase(scanRepository: ScanRepository,
                                     @Named("ui_thread") uiThread: Scheduler,
                                     @Named("executor_thread") executorThread: Scheduler): GetWarehousesUseCase = GetWarehousesUseCase(scanRepository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun providesCreateSourceUseCase(scanRepository: ScanRepository,
                                     @Named("ui_thread") uiThread: Scheduler,
                                     @Named("executor_thread") executorThread: Scheduler): CreateSourceUseCase = CreateSourceUseCase(scanRepository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun providesAddScanPresenter(getWarehousesUseCase: GetWarehousesUseCase, addScanUseCase: AddScanUseCase, createSourceUseCase: CreateSourceUseCase):
            AddScanPresenter = AddScanPresenter(getWarehousesUseCase, addScanUseCase, createSourceUseCase)

}