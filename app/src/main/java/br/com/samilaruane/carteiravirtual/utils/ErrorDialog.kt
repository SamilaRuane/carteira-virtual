package br.com.samilaruane.carteiravirtual.utils

import android.content.Context
import android.support.v7.app.AlertDialog

/**
 * Created by samila on 07/01/18.
 */
class ErrorDialog : Dialog {

    override fun show(ctx: Context, message: String) {
        val alert = AlertDialog.Builder (ctx)
        alert.setMessage("Aconteceu um erro \n $message")
        alert.create().
                show()

    }
}