package br.com.samilaruane.carteiravirtual.utils

import android.content.Context
import android.content.DialogInterface

/**
 * Created by samila on 07/01/18.
 */
interface Dialog {
    fun show (ctx : Context, message : String)
    fun showDialogWithCallback(ctx : Context, message: String, listener : DialogInterface.OnClickListener)
}