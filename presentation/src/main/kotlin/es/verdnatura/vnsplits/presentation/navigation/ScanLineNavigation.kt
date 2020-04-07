package es.verdnatura.vnsplits.presentation.navigation

import es.verdnatura.vnsplits.domain.entity.ScanLine

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
interface ScanLineNavigation {

    fun navigateToGroupedLines(lines: List<ScanLine>, total: Int)

}