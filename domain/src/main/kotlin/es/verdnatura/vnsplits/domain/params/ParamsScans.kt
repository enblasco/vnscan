package es.verdnatura.vnsplits.domain.params


/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
class ParamsScans private constructor(val type: String) {

    companion object{
        fun forScan(type: String): ParamsScans {
            return ParamsScans(type)
        }
    }
}