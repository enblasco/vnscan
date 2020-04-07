package es.verdnatura.vnsplits.presentation.view.view

import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase


/**
 * Created by Enrique Blasco Blanquer on 5/6/17.
 */
interface AddScanView: ListView {

    fun onCompleteAddScan()
    fun onErrorAddScan(error: String, params: AddScanUseCase.Params)

}