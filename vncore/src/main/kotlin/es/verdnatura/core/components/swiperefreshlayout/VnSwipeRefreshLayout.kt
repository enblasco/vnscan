package es.verdnatura.core.components.swiperefreshlayout

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import es.verdnatura.core.R

/**
 * Created by Enrique Blasco Blanquer on 23/3/17.
 */
class VnSwipeRefreshLayout : SwipeRefreshLayout {

    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        setColorScheme(R.color.colorAccent, R.color.colorAccent, R.color.colorAccent, R.color.colorAccent)
    }

    fun showProgress() { isRefreshing = true }

    fun hideProgress() { isRefreshing = false }

    fun disableProgress() { isEnabled = false }

    fun enableProgress() { isEnabled = true }

}