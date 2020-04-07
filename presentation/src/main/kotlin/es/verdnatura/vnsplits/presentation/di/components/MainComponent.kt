package es.verdnatura.vnsplits.presentation.di.components

import dagger.Subcomponent
import es.verdnatura.vnsplits.domain.interactor.scan.GetVersionUseCase
import es.verdnatura.vnsplits.domain.interactor.scan.UpdateUseCase
import es.verdnatura.vnsplits.presentation.di.PerActivity
import es.verdnatura.vnsplits.presentation.di.modules.MainModule
import es.verdnatura.vnsplits.presentation.view.fragment.MainFragment


/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
@PerActivity
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(fragment: MainFragment)
    fun getVersionUseCase(): GetVersionUseCase
    fun updateUseCase(): UpdateUseCase

}