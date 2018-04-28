package br.com.samilaruane.carteiravirtual.extension

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.di.components.AppComponent

/**
 * Created by samila on 24/03/18.
 */
fun Activity.component () : AppComponent = (this.application as App).getComponent()

fun Activity.alert (message : String, listener : DialogInterface.OnClickListener?) {
    val alert = AlertDialog.Builder (this)
    alert.setMessage(message)

    alert.setPositiveButton("Ok",{ dialog, wich ->
            if(listener != null)
                listener.onClick(dialog, wich)
            else
                dialog.dismiss()

    })
    alert.create().
            show()
}