package es.verdnatura.core

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/**
 * Created by Enrique Blasco Blanquer on 30/6/17.
 */
class UtilPermissions {

    companion object{

        val OKPERMISSIONS = 100

        fun getPermissions(activity: Activity, permissions: Array<String>){
            ActivityCompat.requestPermissions(activity,  permissions, OKPERMISSIONS)
        }

        fun checkPermisionReadExternalStorage(context: Context): Boolean{
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                return true
            return false
        }

        fun checkPermisionWriteExternalStorage(context: Context): Boolean{
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                return true
            return false
        }
    }

}