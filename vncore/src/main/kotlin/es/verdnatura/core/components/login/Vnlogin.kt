package es.verdnatura.core.components.login

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import es.verdnatura.core.R
import kotlinx.android.synthetic.main.vnlogin.view.*

/**
 * Created by Enrique Blasco Blanquer on 9/5/17.
 */
class Vnlogin: LinearLayout {

    constructor(context: Context?) : super(context){
        render(context)
    }

    constructor(context: Context?, attributeSet: AttributeSet?): super(context, attributeSet){
        render(context)
    }

    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int): super(context, attributeSet, defStyleAttr){
        render(context)
    }

    private fun render(context: Context?){
        LayoutInflater.from(context).inflate(R.layout.vnlogin, this)
        checkPassword.setOnClickListener { showPassword() }
        edtUser.setFocus()
    }

    private fun showPassword(){
        if(checkPassword.isChecked) edtPassword.textAsPlainText()
        else edtPassword.textAsPassword()
        edtPassword.setCursorPosition(edtPassword.getText().length)
    }

    fun setLogin(login:()->Unit){
        buttonLogin.setOnClickListener { login() }
    }

    fun getUser(): String{
        return edtUser.getText()
    }

    fun getPassword():String{
        return edtPassword.getText()
    }

    fun setRememberPasswordAndShowCheckBox(remember: ()->Unit){
        checkRemember.visibility = View.VISIBLE
        checkRemember.setOnClickListener { remember() }
    }

}