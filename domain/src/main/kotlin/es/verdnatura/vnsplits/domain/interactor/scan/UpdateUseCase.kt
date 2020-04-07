package es.verdnatura.vnsplits.domain.interactor.scan

import es.verdnatura.vnsplits.domain.interactor.UseCase
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.io.File
import javax.inject.Inject
import okio.Okio
import okio.BufferedSource


/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class UpdateUseCase : UseCase<File, UpdateUseCase.Params>{

    val repository: ScanRepository

    @Inject
    constructor(repository: ScanRepository, uiThread: Scheduler, thread: Scheduler): super(uiThread, thread){
        this.repository = repository
    }

    override fun buildUseCaseObservable(params: UpdateUseCase.Params): Observable<File> {

        return repository.getApk().map { s -> writeDataTofile(s, params.file) }
    }

    fun writeDataTofile(buffered: BufferedSource, file: File): File{
        val bufferedSink = Okio.buffer(Okio.sink(file))
        bufferedSink.writeAll(buffered)
        bufferedSink.close()
        return file
    }

    class Params private constructor(val file: File){
        companion object{
            fun forUpdate(file: File): Params{
                return Params(file)
            }
        }
    }

}