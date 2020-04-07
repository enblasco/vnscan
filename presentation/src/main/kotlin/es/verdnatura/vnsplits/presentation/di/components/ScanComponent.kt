package es.verdnatura.vnsplits.presentation.di.components

import dagger.Subcomponent
import es.verdnatura.vnsplits.domain.interactor.scan.*
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.di.modules.ScanModule
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import es.verdnatura.vnsplits.presentation.view.fragment.AddScanFragment
import es.verdnatura.vnsplits.presentation.view.fragment.ScanFragment

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ScanModule::class))
interface ScanComponent {

    fun inject(scanFragment: ScanFragment)
    fun inject(addScanFragment: AddScanFragment)
    fun getScanPresenter(): ScanPresenter
    fun getScanListUseCase(): ScanListUseCase
    fun getShowChildsUseCase(): ShowChildsUseCase
    fun getHideChildsUseCase(): HideChildsUseCase
    fun getAddScanUseCase(): AddScanUseCase
    fun getDeleteScanUseCase(): DeleteScanUseCase
    fun getWarehouseUseCase(): GetWarehousesUseCase
    fun createSourceUseCase(): CreateSourceUseCase

}