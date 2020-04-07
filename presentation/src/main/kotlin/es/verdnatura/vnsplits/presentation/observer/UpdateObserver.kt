package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.presentation.presenter.MainPresenter
import io.reactivex.observers.DisposableObserver
import java.io.File

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class UpdateObserver(val presenter: MainPresenter): DisposableObserver<File>() {
    override fun onComplete() {
        presenter.hideProgress()
    }

    override fun onNext(t: File?) {
        presenter.installApk(t!!)
    }

    override fun onError(e: Throwable?) {
        presenter.errorUpdate(e?.message.toString())
    }
}