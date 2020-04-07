package es.verdnatura.vnsplits.domain.check

import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * Created by Enrique Blasco Blanquer on 21/3/17.
 */
class PreconditionsTest {

    @Rule
    @JvmField
    public var exception = ExpectedException.none()

    @Test
    fun checkNotNull(){
        var message : String = ""
        Preconditions.checkNotNull(message)
    }

    @Test
    fun checkNull(){
        exception.expect(NullPointerException::class.java)
        Preconditions.checkNotNull(null)
    }
}