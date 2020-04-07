package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.presentation.presenter.MainPresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class GetVersionObserver(val presenter: MainPresenter): DisposableObserver<Boolean>() {
    override fun onError(e: Throwable?) {}

    override fun onNext(t: Boolean?) {
        presenter.onGetVersion(t!!)
    }

    override fun onComplete() {}
}