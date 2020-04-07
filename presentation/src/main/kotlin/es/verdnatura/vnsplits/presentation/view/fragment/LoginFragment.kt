package es.verdnatura.vnsplits.presentation.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import es.verdnatura.core.UtilPermissions
import es.verdnatura.core.components.dialogs.retry.DialogRetry
import es.verdnatura.vnsplits.data.preferences.Preferences
import es.verdnatura.vnsplits.presentation.di.components.LoginComponent
import es.verdnatura.vnsplits.presentation.presenter.LoginPresenter
import es.verdnatura.vnsplits.presentation.view.view.LoginView
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject


/**
 * Created by Enrique Blasco Blanquer on 9/5/17.
 */
class LoginFragment(layout: Int): BaseFragment(layout), LoginView {

    @Inject lateinit var loginPresenter: LoginPresenter
    @Inject lateinit var preferences: Preferences
    private val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.getComponent(LoginComponent::class.java).inject(this)
        loginPresenter.setView(this)
        vnLogin.setLogin { checkPermission() }
        if(preferences.getUser() != "")
            navigateToMain()
    }

    override fun navigateToMain() {
        userNavigatior.navigate()
    }

    override fun showProgress() {
        vnProgress.show()
    }

    override fun hideProgress() {
        vnProgress.hide()
    }

    override fun showError(error: String) {
        val dialog = DialogRetry(error, { login() })
        dialog.show(fragmentManager, "loginFragment")
    }

    override fun saveUser() {
        preferences.saveUserAndPass(vnLogin.getUser(), vnLogin.getPassword())
    }

    fun login(){
        loginPresenter.login(vnLogin.getUser(), vnLogin.getPassword())
    }

    fun checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M &&
                (!UtilPermissions.checkPermisionReadExternalStorage(activity) ||
                UtilPermissions.checkPermisionWriteExternalStorage(activity)))
                    UtilPermissions.getPermissions(activity, permissions)
        else login()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == UtilPermissions.OKPERMISSIONS) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                login()
            }
        }
    }

}