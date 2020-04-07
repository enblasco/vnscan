package es.verdnatura.vnsplits.presentation.view.fragment

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.verdnatura.vnsplits.presentation.di.HasComponent
import es.verdnatura.vnsplits.presentation.navigation.UserNavigator
import es.verdnatura.vnsplits.presentation.view.activity.BaseActivity

/**
 * Created by Enrique Blasco Blanquer on 21/2/17.
 */
open class BaseFragment(val layout: Int) : Fragment(){

    val app get() = (activity as BaseActivity).app
    lateinit var userNavigatior: UserNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if(activity is UserNavigator)
            userNavigatior = activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentView = inflater?.inflate(layout, container, false)
        return fragmentView!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun showToastMessage(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getSubComponent())
    }

}