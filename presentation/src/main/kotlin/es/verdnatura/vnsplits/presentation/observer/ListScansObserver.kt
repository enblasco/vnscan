package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
class ListScansObserver(val scanPresenter: ScanPresenter): DisposableObserver<List<Scan>>() {

    override fun onComplete() {
        scanPresenter.onFinishRepository()
    }

    override fun onError(e: Throwable?) {
        scanPresenter.onErrorListScans(e!!.message.toString())
    }

    override fun onNext(t: List<Scan>?) {
        scanPresenter.createAdapter(t!!)
    }
}