package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
class GroupLinesObserver(val presenter: ScanLinePresenter): DisposableObserver<List<ScanLine>>() {
    override fun onError(e: Throwable?) {}

    override fun onNext(t: List<ScanLine>?) {
        presenter.showLinesGrouped(t!!)
    }

    override fun onComplete() {}
}