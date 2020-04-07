package es.verdnatura.core.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.verdnatura.core.R
import es.verdnatura.core.UtilsResources
import kotlinx.android.synthetic.main.vnmenuitem.view.*


/**
 * Created by Enrique Blasco Blanquer on 19/5/17.
 */
class MenuAdapter(val items: List<MenuItem>, val listener: (MenuItem) -> Unit) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vnmenuitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MenuItem, listener: (MenuItem) -> Unit) = with(itemView) {
            menuItemImage.setImageDrawable(UtilsResources.getDrawable(context, item.imageRecourse))
            menuItemText.text = item.text
            setOnClickListener { listener(item) }
        }
    }

    data class MenuItem(val id: Int, val imageRecourse: Int, val text: String)
}