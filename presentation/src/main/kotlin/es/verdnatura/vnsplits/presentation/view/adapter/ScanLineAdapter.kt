package es.verdnatura.vnsplits.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.R
import kotlinx.android.synthetic.main.item_scan_line.view.*

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ScanLineAdapter(var items: MutableList<ScanLine>, val listener: (ScanLine) -> Unit) : RecyclerView.Adapter<ScanLineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scan_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ScanLine, listener: (ScanLine) -> Unit) = with(itemView) {
            code.text = item.Code
            value.text = item.Value
            info.text = item.Info
            if(item.Value.length > 0) value.visibility = View.VISIBLE else value.visibility = View.GONE
            if(item.Info.length > 0) info.visibility = View.VISIBLE else info.visibility = View.GONE
            if(item.Total > 0)
            {
                total.text = item.Total.toString()
                switchDelete.visibility = View.GONE
                total.visibility = View.VISIBLE
            }
            else{
                switchDelete.setChecked(item.Delete)
                switchDelete.onChange { listener(item) }
                switchDelete.visibility = View.VISIBLE
            }
        }
    }
}