package es.verdnatura.core.components.editscan

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import es.verdnatura.core.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Enrique Blasco Blanquer on 9/3/17.
 */
class VnEditScan: RelativeLayout {

    private lateinit var editScan: EditText
    private lateinit var camera: ImageView
    private var runThreadFocus = true

    constructor(context: Context?): super(context){
        render(context)
        setFocus()
    }

    constructor(context: Context?, attributeSet: AttributeSet?): super(context, attributeSet){
        render(context)
        setFocus()
    }

    fun render(context: Context?){
        LayoutInflater.from(context).inflate(R.layout.vneditscan, this)
        editScan = this.findViewById(es.verdnatura.core.R.id.edit_scan) as EditText
        camera = this.findViewById(es.verdnatura.core.R.id.edit_cam) as ImageView
        camera.setOnClickListener { this }
    }

    fun onScan(action: (String) -> Unit) {
        editScan.setOnEditorActionListener {
            _, actionId, event ->
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN)
                action(editScan.text.toString())
                true
        }
    }

    fun getText(): String{
        return editScan.text.toString()
    }

    fun setTextScan(code: String){
        editScan.setText(code)
    }

    fun setScanAction(action: () -> Unit) {
        camera.setOnClickListener { action() }
    }

    fun clear(){
        editScan.setText("")
    }

    fun setFocus() {
        doAsync {
            Thread.sleep(1000)
            uiThread {
                if(runThreadFocus) {
                    editScan.requestFocus()
                    setFocus()
                }
            }
        }
    }

    fun show() {
        this.visibility = View.VISIBLE
    }

    fun hide() {
        this.visibility = View.GONE
    }

    fun isVisible(): Boolean{
        return visibility == View.VISIBLE
    }

    fun stopFocusThread(){
        runThreadFocus = false
    }

}