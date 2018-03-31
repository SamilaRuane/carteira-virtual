package br.com.samilaruane.carteiravirtual.utils

/**
 * Created by samila on 14/03/18.
 */
interface EventResponseListener <in T> {
    fun onSuccess (obj : T)
    fun onError (errorMessage : String)
}