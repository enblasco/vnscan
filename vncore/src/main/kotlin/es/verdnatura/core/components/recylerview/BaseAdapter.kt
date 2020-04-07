package es.verdnatura.core.components.recylerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Enrique Blasco Blanquer on 5/7/17.
 */
abstract class BaseAdapter<T>(val layout: Int, var items: List<T>, var listener: (T) -> Unit): RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T>{
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, true)
        return createViewHolder(view)
    }

    abstract fun createViewHolder(view: View): ViewHolder<T>

    abstract class ViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView){
        abstract fun bind(item: T, listener: (T) -> Unit)
    }

}