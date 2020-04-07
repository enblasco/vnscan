package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 16/6/17.
 */
class DeleteScanLineObserver(val presenter: ScanLinePresenter): DisposableObserver<List<ScanLine>>() {

    override fun onNext(t: List<ScanLine>?) {
        presenter.onChangeLines(t!!)
    }

    override fun onComplete() {
        presenter.hideProgress()
    }

    override fun onError(e: Throwable?) {
        presenter.onErrorDeleteLines(e?.message.toString())
    }
}