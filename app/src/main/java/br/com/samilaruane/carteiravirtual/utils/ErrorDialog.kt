package br.com.samilaruane.carteiravirtual.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

/**
 * Created by samila on 07/01/18.
 */
class ErrorDialog : Dialog {

    override fun show(ctx: Context, message: String) {
        val alert = AlertDialog.Builder (ctx)
        alert.setMessage(message)
        alert.create().
                show()

    }

    override fun showDialogWithCallback(ctx: Context, message: String, listener: DialogInterface.OnClickListener) {
        val alert = AlertDialog.Builder (ctx)
        alert.setMessage(message).
                setPositiveButton("Ok", {dialog, i -> listener.onClick(dialog, i)})
                alert.create().
                show()
    }
}