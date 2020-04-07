package es.verdnatura.core.components.snackbar

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Enrique Blasco Blanquer on 18/4/17.
 */
class VnSnackbar{

    companion object{
        fun make(view: View, msg: String){
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                    .setAction("Ok", View.OnClickListener {  })
                    .show()
        }
    }

}