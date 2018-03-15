package br.com.samilaruane.carteiravirtual.utils

/**
 * Created by samila on 14/03/18.
 */
interface OnEventResponse {
    fun onSuccess ()
    fun onError (errorMessage : String)
}