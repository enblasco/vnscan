package es.verdnatura.vnsplits.presentation.presenter

import android.os.Environment
import es.verdnatura.vnsplits.domain.interactor.scan.GetVersionUseCase
import es.verdnatura.vnsplits.domain.interactor.scan.UpdateUseCase
import es.verdnatura.vnsplits.presentation.observer.GetVersionObserver
import es.verdnatura.vnsplits.presentation.observer.UpdateObserver
import es.verdnatura.vnsplits.presentation.view.view.BaseView
import es.verdnatura.vnsplits.presentation.view.view.MainView
import java.io.File
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class MainPresenter : BasePresenter {

    val getVersionUseCase: GetVersionUseCase
    val updateUseCase: UpdateUseCase
    private lateinit var view: MainView

    private val FILE_PATH = Environment.getExternalStorageDirectory().absolutePath

    @Inject
    constructor(getVersionUseCase: GetVersionUseCase, updateUseCase: UpdateUseCase){
        this.getVersionUseCase = getVersionUseCase
        this.updateUseCase = updateUseCase
    }

    override fun onDestroy() {}

    override fun setView(v: BaseView) {
        view = v as MainView
    }

    fun getVersion(v: Int){
        getVersionUseCase.execute(GetVersionObserver(this), GetVersionUseCase.Params.forGetVersion(v))
    }

    fun onGetVersion(isNewVersion: Boolean){
        if (isNewVersion) view.onNewVersion()
    }

    fun update(){
        view.showProgress()
        updateUseCase.execute(UpdateObserver(this), UpdateUseCase.Params.forUpdate(createFile()!!))
    }

    fun hideProgress(){
        view.hideProgress()
    }

    fun errorUpdate(error: String){
        view.errorUpdate(error)
        hideProgress()
    }

    fun createFile(): File? {
        var file: File? = null
        try {
            val dir = File(FILE_PATH, "/Verdnatura/download")
            dir.mkdirs()
            file = File(dir, "vnsplits")
            if (file.exists()) file.delete()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return file
    }

    fun installApk(file: File) {
        view.installApk(file)
    }
}