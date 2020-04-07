package es.verdnatura.vnsplits.presentation.presenter

import es.verdnatura.vnsplits.domain.interactor.scan.AddScanUseCase

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
interface AddScanFunctions {

    fun onFinishAddScan(scan: Int)
    fun onErrorCreateScan(message: String, params: AddScanUseCase.Params)
}