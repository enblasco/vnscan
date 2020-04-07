package es.verdnatura.core.components.progress

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import es.verdnatura.core.R

/**
 * Created by Enrique Blasco Blanquer on 17/2/17.
 */
class VnProgress : LinearLayout {

    constructor(context: Context?) : super(context){
        render(context)
    }

    constructor(context: Context?, attributeSet: AttributeSet?): super(context, attributeSet){
        render(context)
    }

    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int): super(context, attributeSet, defStyleAttr){
        render(context)
    }

    fun render(context: Context?){
        LayoutInflater.from(context).inflate(R.layout.vnprogress, this)
    }

    fun show(){
        this.visibility = View.VISIBLE
        changeBrotherVisibility(View.GONE)
    }

    fun hide(){
        this.visibility = View.GONE
        changeBrotherVisibility(View.VISIBLE)
    }

    fun changeBrotherVisibility(visibility: Int){

        var parentViewGroup = this.parent as ViewGroup
        var childsCount = (parentViewGroup).childCount
        for(item in 0..childsCount-1){
            var view = parentViewGroup.getChildAt(item)
            if(view != this)
                view.visibility = visibility
        }

    }

}