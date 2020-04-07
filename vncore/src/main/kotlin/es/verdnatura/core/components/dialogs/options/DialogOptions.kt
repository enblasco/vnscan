package es.verdnatura.core.components.dialogs.options

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import es.verdnatura.core.R
import es.verdnatura.core.UtilsResources

/**
 * Created by Enrique Blasco Blanquer on 24/5/17.
 */
class DialogOptions {

    val dialog: AlertDialog.Builder

    constructor(context: Context, options: List<DialogOptionsItem>){
        dialog = createDialog(context, options)
    }

    private fun createDialog(context: Context, options: List<DialogOptionsItem>): AlertDialog.Builder {
        var dialog = AlertDialog.Builder(context)
        val titles = options.map { o -> o.option }
        dialog.setTitle(UtilsResources.getResourceString(context, R.string.select_option))
        dialog.setItems(titles.toTypedArray() , DialogInterface.OnClickListener {
            dialog, which ->  options[which].action()
            dialog.dismiss()
        })
        return dialog
    }

    fun show(){
        dialog.show()
    }
}