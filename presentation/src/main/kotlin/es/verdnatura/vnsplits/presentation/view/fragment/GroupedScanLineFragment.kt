package es.verdnatura.vnsplits.presentation.view.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.view.adapter.ScanLineAdapter

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
class GroupedScanLineFragment(val lines: List<ScanLine>, val total: Int): ListFragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateAdapter(lines)
        createAdapter(lines)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_grouped_lines, menu)
        menu?.findItem(R.id.menuTotalLines)?.setTitle(total.toString())
    }

    override fun <T> generateAdapter(elements: List<T>): RecyclerView.Adapter<*> {
        return ScanLineAdapter(elements as MutableList<ScanLine>, {})
    }

    override fun onRefresh() {}

    override fun <T> changeItems(elements: List<T>) {}

    override fun actionErrorList() {}

}