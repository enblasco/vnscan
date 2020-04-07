package es.verdnatura.vnsplits.presentation.presenter

import android.support.annotation.NonNull
import es.verdnatura.vnsplits.presentation.view.view.BaseView

/**
 * Created by Enrique Blasco Blanquer on 20/2/17.
 */
interface BasePresenter {

    fun onDestroy()
    fun setView(@NonNull v: BaseView)
}