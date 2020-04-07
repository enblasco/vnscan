package es.verdnatura.vnsplits.presentation.di.components

import dagger.Subcomponent
import es.verdnatura.vnsplits.domain.interactor.scanline.*
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.di.modules.ScanLineModule
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import es.verdnatura.vnsplits.presentation.view.fragment.ScanLineFragment

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ScanLineModule::class))
interface ScanLineComponent {

    fun inject(fragment: ScanLineFragment)
    fun getScanLinePresenter(): ScanLinePresenter
    fun getListScanLineUseCase(): ListScanLineUseCase
    fun getInsertScanLineUseCase(): InsertScanLineUseCase
    fun createScanLineUseCase(): CreateLinesUseCase
    fun deleteScanLineUseCase(): DeleteScanLineUseCase
    fun grouScanLineUseCase(): GroupLinesUseCase
}