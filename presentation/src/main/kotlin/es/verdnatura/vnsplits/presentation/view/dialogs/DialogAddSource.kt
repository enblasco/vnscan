package es.verdnatura.vnsplits.presentation.view.dialogs

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import es.verdnatura.vnsplits.presentation.R
import kotlinx.android.synthetic.main.dialog_add_source.view.*


/**
 * Created by Enrique Blasco Blanquer on 25/5/17.
 */
class DialogAddSource(val accept: (name: String) -> Unit): DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater?.inflate(R.layout.dialog_add_source, container, false)!!
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        rootView.createSource.setOnClickListener {
            accept(rootView.sourceName.getText())
            dialog.dismiss()
        }
        return rootView
    }
}