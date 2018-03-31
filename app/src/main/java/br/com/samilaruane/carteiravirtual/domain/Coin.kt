package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.utils.EventResponseListener

/**
 * Created by samila on 18/12/17.
 */
interface Coin {
    fun loadCoin (listener: EventResponseListener<String>)
    fun getSalePrice ( ) : Double
    fun getPurchaseQuotation ( ) : Double
    fun getCoinInitials () : String
}