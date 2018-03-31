package br.com.samilaruane.carteiravirtual.utils

/**
 * Created by samila on 14/02/18.
 */
interface OnDatabaseAccessListener <in T> {
    fun onSelectSuccess (obj : T)
}