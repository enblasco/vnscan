package es.verdnatura.vnsplits.presentation.view.view

import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase

/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
interface ScanView: ListView {

    fun showErrorAddChild(error: String, params: AddScanUseCase.Params)
    fun showErrorDeleteScan(error: String, id: Int)

    fun scan(id: Int)
}