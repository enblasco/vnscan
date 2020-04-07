package es.verdnatura.vnsplits.presentation.view.dialogs

import android.app.AlertDialog
import android.content.Context

import es.verdnatura.core.UtilsResources
import es.verdnatura.vnsplits.presentation.R

/**
 * Created by Enrique Blasco Blanquer on 24/5/17.
 */
class DialogDeleteScanLines {

    val dialog : AlertDialog.Builder

    constructor(context: Context, delete: () -> Unit){
        dialog = AlertDialog.Builder(context)
        dialog.setTitle(UtilsResources.getResourceString(context, R.string.delete_lines))
        dialog.setMessage(UtilsResources.getResourceString(context, R.string.delete_lines_message))
        dialog.setPositiveButton(UtilsResources.getResourceString(context, R.string.accept)) { dialog, _ -> run {
                delete()
                dialog.cancel()
            }
        }

        dialog.setNegativeButton(UtilsResources.getResourceString(context, R.string.cancel), null)
    }

    fun show(){
        dialog.show()
    }

}