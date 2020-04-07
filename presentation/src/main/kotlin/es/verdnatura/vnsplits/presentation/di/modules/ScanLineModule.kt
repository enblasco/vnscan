package es.verdnatura.vnsplits.presentation.di.modules

import dagger.Module
import dagger.Provides
import es.verdnatura.vnsplits.domain.interactor.scanline.*
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import es.verdnatura.vnsplits.presentation.view.activity.ScanLineActivity
import io.reactivex.Scheduler
import javax.inject.Named

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
@Module
class ScanLineModule(private val activity: ScanLineActivity) {

    @Provides
    @PerActivity
    fun getScanPresenter(listScanLineUseCase: ListScanLineUseCase, insertScanLineUseCase: InsertScanLineUseCase, createLinesUseCase: CreateLinesUseCase,
                         deleteScanLineUseCase: DeleteScanLineUseCase, groupLinesUseCase: GroupLinesUseCase):
            ScanLinePresenter = ScanLinePresenter(listScanLineUseCase, insertScanLineUseCase, createLinesUseCase, deleteScanLineUseCase, groupLinesUseCase )

    @Provides
    @PerActivity
    fun getListScanLineUSeCase(repository: ScanLineRepository,
                               @Named("ui_thread") uiThread: Scheduler,
                               @Named("executor_thread") executorThread: Scheduler): ListScanLineUseCase = ListScanLineUseCase(repository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun getInsertScanLineUseCase(repository: ScanLineRepository,
                                 @Named("ui_thread") uiThread: Scheduler,
                                 @Named("executor_thread") executorThread: Scheduler): InsertScanLineUseCase = InsertScanLineUseCase(repository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun createScanLineUseCase(repository: ScanLineRepository,
                                 @Named("ui_thread") uiThread: Scheduler,
                                 @Named("executor_thread") executorThread: Scheduler): CreateLinesUseCase = CreateLinesUseCase(repository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun deleteScanLineUseCase(repository: ScanLineRepository,
                              @Named("ui_thread") uiThread: Scheduler,
                              @Named("executor_thread") executorThread: Scheduler): DeleteScanLineUseCase = DeleteScanLineUseCase(repository, uiThread, executorThread)

    @Provides
    @PerActivity
    fun groupedScanLinesUseCase(@Named("ui_thread") uiThread: Scheduler,
                              @Named("executor_thread") executorThread: Scheduler): GroupLinesUseCase = GroupLinesUseCase(uiThread, executorThread)


}