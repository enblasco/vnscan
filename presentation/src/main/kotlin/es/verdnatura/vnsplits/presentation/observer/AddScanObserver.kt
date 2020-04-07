package es.verdnatura.vnsplits.presentation.observer

import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase
import es.verdnatura.vnsplits.presentation.presenter.AddScanFunctions
import io.reactivex.observers.DisposableObserver

/**
 * Created by Enrique Blasco Blanquer on 25/5/17.
 */
class AddScanObserver(val presenter: AddScanFunctions, val params: AddScanUseCase.Params): DisposableObserver<Int>() {

    override fun onComplete() { }

    override fun onNext(t: Int?) {presenter.onFinishAddScan(t!!)}

    override fun onError(e: Throwable?) { presenter.onErrorCreateScan(e?.message.toString(), params) }
}