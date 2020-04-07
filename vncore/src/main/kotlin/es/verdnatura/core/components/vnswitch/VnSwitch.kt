package es.verdnatura.core.components.vnswitch

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import es.verdnatura.core.R
import kotlinx.android.synthetic.main.vnswitch.view.*

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class VnSwitch: LinearLayout {

    constructor(context: Context?, attributeSet: AttributeSet?): super(context, attributeSet){
        render(context)
    }

    fun render(context: Context?){
        LayoutInflater.from(context).inflate(R.layout.vnswitch, this)
    }

    fun onChange(change: ()->Unit){
        vnswitch.setOnClickListener { change() }
    }

    fun setChecked(value: Boolean){
        vnswitch.isChecked = value
    }
}