package es.verdnatura.vnsplits.presentation.view.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.presentation.R
import kotlinx.android.synthetic.main.image_more.view.*
import kotlinx.android.synthetic.main.item_scans.view.*

/**
 * Created by Enrique Blasco Blanquer on 23/5/17.
 */
class ScansAdapter(var items: MutableList<Scan>, val listener: (Scan) -> Unit, val showMore: (Scan) -> Unit) : RecyclerView.Adapter<ScansAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scans, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, showMore)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Scan, listener: (Scan) -> Unit, showMore: (Scan) -> Unit) = with(itemView) {
            scanTitle.text = item.Name
            scanChilds.text = item.Childs.size.toString()
            scanLines.text = item.NumLines.toString()
            scanDate.text = item.Date

            if(item.Level == 2 ) scanTitle.setTypeface(null, Typeface.BOLD) else scanTitle.setTypeface(null, Typeface.NORMAL)
            if(item.Childs.size == 0) scanMore.visibility = View.INVISIBLE else scanMore.visibility = View.VISIBLE
            if(item.Displayed) scanMore.setImageResource(R.drawable.ic_less) else scanMore.setImageResource(R.drawable.ic_more)

            scanMore.setOnClickListener { showMore(item) }

            setOnClickListener { listener(item) }

        }

    }

}