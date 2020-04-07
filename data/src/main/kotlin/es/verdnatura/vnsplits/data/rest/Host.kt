package es.verdnatura.vnsplits.data.rest

import android.os.Build

/**
 * Created by Enrique Blasco Blanquer on 12/4/17.
 */
class Host {

    companion object{

        private val TESTHOST = "https://app.verdnatura.es/"
        private val RELEASEHOST = "https://app.verdnatura.es/"
        //private val RELEASEHOST = "http://192.168.1.33:8000/"

        fun getHost(): String{
            if(Build.FINGERPRINT.startsWith("generic"))
                return TESTHOST
            return RELEASEHOST
        }
    }

}