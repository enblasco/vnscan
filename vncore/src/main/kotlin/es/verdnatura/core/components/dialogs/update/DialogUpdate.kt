package es.verdnatura.core.components.dialogs.update

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import es.verdnatura.core.R
import es.verdnatura.core.UtilsResources

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class DialogUpdate(val update: () -> Unit): DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setMessage(UtilsResources.getResourceString(activity, R.string.update_message))
                .setTitle("")
                .setPositiveButton(UtilsResources.getResourceString(activity, R.string.accept))
                { _, _ -> run {
                        update()
                        dialog.cancel()
                    }

                }

        return builder.create()
    }

}