package es.verdnatura.vnsplits.domain.params

import es.verdnatura.vnsplits.domain.entity.Scan

/**
 * Created by Enrique Blasco Blanquer on 24/5/17.
 */
class ParamsShowHideChilds private constructor(val scans: MutableList<Scan>, val scan: Scan){
    companion object{
        fun forShowChilds(scans: MutableList<Scan>, scan: Scan): ParamsShowHideChilds{
            return ParamsShowHideChilds(scans, scan)
        }
    }
}