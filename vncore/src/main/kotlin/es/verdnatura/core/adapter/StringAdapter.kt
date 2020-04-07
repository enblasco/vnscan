package es.verdnatura.core.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.verdnatura.core.R
import kotlinx.android.synthetic.main.view_string.view.*

/**
 * Created by Enrique Blasco Blanquer on 7/6/17.
 */
class StringAdapter(var items: List<String>, val listener: (String) -> Unit) : RecyclerView.Adapter<StringAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_string, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String, listener: (String) -> Unit) = with(itemView) {
            textString.text = item
            setOnClickListener { listener(item) }
        }

    }
}