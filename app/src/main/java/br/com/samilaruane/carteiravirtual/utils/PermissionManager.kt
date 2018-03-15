package br.com.samilaruane.carteiravirtual.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Created by samila on 25/01/18.
 */
class PermissionManager {

    companion object {


        fun checkPermission(context: Context, permission: String): Boolean {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_DENIED) {
                return true
            }
            return false
        }

        fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }
}