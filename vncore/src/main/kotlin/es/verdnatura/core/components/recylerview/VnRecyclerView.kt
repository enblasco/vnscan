package es.verdnatura.core.components.recylerview

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet


/**
 * Created by Enrique Blasco Blanquer on 7/3/17.
 */
open class VnRecyclerView: RecyclerView {

    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)


    fun setAdapterList(adapter: Adapter<*>?){
        this.adapter = adapter
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun setAdapterGrid(adapter: Adapter<*>?, columns: Int){
        this.adapter = adapter
        this.layoutManager = GridLayoutManager(context, columns)
    }

}