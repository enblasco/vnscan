package es.verdnatura.vnsplits.presentation.observer

import android.util.Log
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.presentation.presenter.ScanPresenter
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
class ShowHideChildsObserver(val scanPresenter: ScanPresenter): DisposableObserver<List<Scan>>() {
    override fun onError(e: Throwable?) {
        Log.e("E", e?.message)
    }

    override fun onNext(t: List<Scan>?) {
        scanPresenter.notifyDataHasChanged(t!!)
    }

    override fun onComplete() {}
}