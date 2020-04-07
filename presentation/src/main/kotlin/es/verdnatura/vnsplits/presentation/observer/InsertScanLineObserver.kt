package es.verdnatura.vnsplits.presentation.observer

import android.util.Log
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.presenter.ScanLinePresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 12/6/17.
 */
class InsertScanLineObserver(val scanLinePresenter: ScanLinePresenter): DisposableObserver<ScanLine>() {

    override fun onError(e: Throwable?) {
        Log.e("Error insert scan", e?.message.toString())
    }

    override fun onComplete() {}

    override fun onNext(t: ScanLine?) {
        scanLinePresenter.addScanLine(t!!)
    }
}