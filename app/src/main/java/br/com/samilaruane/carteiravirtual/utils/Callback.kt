package br.com.samilaruane.carteiravirtual.utils

/**
 * Created by samila on 14/02/18.
 */
interface Callback<in T> {
    fun onSuccess(obj : T)
}