package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 13/6/17.
 */
class CreateScanLineObserver(val scanLinePresenter: ScanLinePresenter): DisposableObserver<List<ScanLine>>() {

    override fun onComplete() {
        scanLinePresenter.hideProgress()
    }

    override fun onNext(t: List<ScanLine>?) {
        scanLinePresenter.onChangeLines(t!!)
    }

    override fun onError(e: Throwable?) {
        scanLinePresenter.onErrorCreateLines(e?.message.toString())
    }
}