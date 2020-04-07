package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ListScanLineObserver(val scanLinePresenter: ScanLinePresenter): DisposableObserver<List<ScanLine>>() {
    override fun onNext(t: List<ScanLine>?) {
        scanLinePresenter.onListScanLine(t!!)
    }

    override fun onError(e: Throwable?) {
        scanLinePresenter.onErrorListScanLine(e?.message.toString())
    }

    override fun onComplete() {
        scanLinePresenter.hideProgress()
    }
}