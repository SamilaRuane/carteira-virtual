package br.com.samilaruane.carteiravirtual.domain

/**
 * Created by samila on 18/12/17.
 */
interface Coin {
    fun getSalePrice () : Double
    fun getPurchaseQuotation () : Double
    fun getCoinInitials () : String
}