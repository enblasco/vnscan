package es.verdnatura.vnsplits.domain.interactor

import es.verdnatura.vnsplits.domain.check.Preconditions
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.disposables.Disposable

/**
 * Created by Enrique Blasco Blanquer on 16/2/17.
 */
abstract class UseCase<T, Params> {

    val uiThread: Scheduler
    val mExecutorThread: Scheduler
    var disposables: CompositeDisposable

    constructor(uiThread: Scheduler, mExecutorThread: Scheduler){
        this.uiThread = uiThread
        this.mExecutorThread = mExecutorThread
        this.disposables = CompositeDisposable()
    }

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params) {
        Preconditions.checkNotNull(params)
        Preconditions.checkNotNull(observer)
        val observable: Observable<T> = this.buildUseCaseObservable(params)
                .subscribeOn(mExecutorThread)
                .observeOn(uiThread)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}