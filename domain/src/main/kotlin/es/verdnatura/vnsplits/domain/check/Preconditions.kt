package es.verdnatura.vnsplits.domain.check


/**
 * Created by Enrique Blasco Blanquer on 16/2/17.
 */
class Preconditions {

    companion object{
        fun checkNotNull(obj: Any?): Any{
            if(obj == null){
                throw NullPointerException()
            }
            return obj
        }
    }
}