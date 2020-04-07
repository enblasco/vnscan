package es.verdnatura.core.components.edittext

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import es.verdnatura.core.R
import kotlinx.android.synthetic.main.vnedittext.view.*

/**
 * Created by Enrique Blasco Blanquer on 25/5/17.
 */
class VnEditText: RelativeLayout {

    constructor(context: Context?, attributeSet: AttributeSet?): super(context, attributeSet){
        render(context, attributeSet)
        setFocus()
        clearVnEditText.setOnClickListener { clear() }
    }

    fun render(context: Context?, attributeSet: AttributeSet?){
        LayoutInflater.from(context).inflate(R.layout.vnedittext, this)

        val attrs = context?.obtainStyledAttributes(attributeSet, R.styleable.VnEditText)
        val hint = attrs?.getString(R.styleable.VnEditText_hint)
        val isPassword = attrs?.getBoolean(R.styleable.VnEditText_isPassword, false)

        if (hint != null) vnEditText.hint = hint
        if (isPassword!!) textAsPassword()

        attrs?.recycle()  // Do this when done.
    }

    fun setFocus() {
        vnEditText.requestFocus()
    }

    fun clear(){
        vnEditText.setText("")
    }

    fun getText(): String{
        return vnEditText.text.toString()
    }

    fun setText(text: String){
        vnEditText.setText(text)
    }

    fun textAsPassword(){
        vnEditText.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    fun textAsPlainText(){
        vnEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    fun setCursorPosition(position: Int){
        vnEditText.setSelection(position)
    }

}