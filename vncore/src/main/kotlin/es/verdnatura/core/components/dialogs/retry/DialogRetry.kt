package es.verdnatura.core.components.dialogs.retry

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import es.verdnatura.core.R
import es.verdnatura.core.UtilsResources


/**
 * Created by Enrique Blasco Blanquer on 2/3/17.
 */
class DialogRetry(val message: String, val retry: () -> Unit): DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setMessage(message)
                .setTitle("Error")
                .setPositiveButton(UtilsResources.getResourceString(activity, R.string.retry)) { _, _ -> run {
                        retry()
                        dialog.cancel()
                    }
                }

        return builder.create()
    }

}