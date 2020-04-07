package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 26/5/17.
 */
class DeleteScanObserver(val scanPresenter: ScanPresenter, val id: Int): DisposableObserver<List<Scan>>() {

    override fun onNext(t: List<Scan>?) {
        scanPresenter.notifyDataHasChanged(t!!)
    }

    override fun onComplete() {
        scanPresenter.onFinishRepository()
    }

    override fun onError(e: Throwable?) {
        scanPresenter.dowloadScans();
        //scanPresenter.onErrorDeleteScan(e?.message.toString(), id)
    }
}