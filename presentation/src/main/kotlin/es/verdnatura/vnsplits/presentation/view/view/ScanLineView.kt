package es.verdnatura.vnsplits.presentation.view.view

import es.verdnatura.vnsplits.domain.entity.ScanLine

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
interface ScanLineView: ListView {

    fun addScanLine(scanLine: ScanLine)
    fun onErrorCreateScanLine(error:String)
    fun onErrorDeleteScanLine(error: String)
    fun showLinesGrouped(lines: List<ScanLine>)
}