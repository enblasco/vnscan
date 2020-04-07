package es.verdnatura.vnsplits.presentation.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import es.verdnatura.core.UtilsResources
import es.verdnatura.core.adapter.MenuAdapter
import es.verdnatura.core.components.dialogs.retry.DialogRetry
import es.verdnatura.core.components.dialogs.update.DialogUpdate
import es.verdnatura.vnsplits.data.preferences.Preferences
import es.verdnatura.vnsplits.domain.entity.ScanType
import es.verdnatura.vnsplits.presentation.R
import es.verdnatura.vnsplits.presentation.di.components.MainComponent
import es.verdnatura.vnsplits.presentation.navigation.MainNavigator
import es.verdnatura.vnsplits.presentation.presenter.MainPresenter
import es.verdnatura.vnsplits.presentation.view.view.MainView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.progress.*
import java.io.File
import javax.inject.Inject

/**
 * Created by Enrique Blasco Blanquer on 23/6/17.
 */
class MainFragment: BaseFragment(R.layout.fragment_main), MainView {

    val NUMBER_OF_COLUMNS = 2

    @Inject lateinit var preferences: Preferences
    @Inject lateinit var presenter: MainPresenter

    lateinit var navigator: MainNavigator

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if(activity is MainNavigator)
            navigator = activity
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComponent(MainComponent::class.java).inject(this)
        presenter.setView(this)
        presenter.getVersion(getVersionApp())
        createAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menuLogout -> { logout()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun installApk(file: File) {
       navigator.installApk(file)
    }

    override fun showProgress() {
        vnProgress.show()
    }

    override fun hideProgress() {
        vnProgress.hide()
    }

    override fun errorUpdate(error: String) {
        DialogRetry(error, { update() }).show(fragmentManager, "update")
    }

    fun getVersionApp(): Int{
        return activity.packageManager.getPackageInfo(activity.packageName, 0).versionCode
    }

    override fun onNewVersion() {
        DialogUpdate({ update() }).show(fragmentManager, "update")
    }

    fun update(){
        presenter.update()
    }

    fun logout(){
        preferences.removeUserAndPass()
        navigator.navigateToLogin()
    }

    fun createAdapter(){
        mainListItems.setAdapterGrid(MenuAdapter(getItemsMenu(), {navigation(it.id)}), NUMBER_OF_COLUMNS)
    }

    fun getItemsMenu(): List<MenuAdapter.MenuItem>{
        return mutableListOf(
                MenuAdapter.MenuItem(Types.AIRPORT.type, R.drawable.ic_item_airport, UtilsResources.getResourceString(activity, R.string.airport)),
                MenuAdapter.MenuItem(Types.EXPEDITIONS.type, R.drawable.ic_item_expeditions, UtilsResources.getResourceString(activity, R.string.expeditions))
        )
    }

    fun navigation(id: Int){
        when(id){
            Types.AIRPORT.type -> navigator.navigateToScan(ScanType.BUYS.toString())
            Types.EXPEDITIONS.type -> navigator.navigateToScan(ScanType.EXPEDITIONS.toString())
        }
    }

    enum class Types(val type: Int){
        AIRPORT(1),
        EXPEDITIONS(2)
    }

}