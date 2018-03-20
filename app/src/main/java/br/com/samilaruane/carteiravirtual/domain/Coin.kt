package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.utils.EventResponseListener

/**
 * Created by samila on 18/12/17.
 */
interface Coin {
    fun getSalePrice (listener: EventResponseListener<Double>)
    fun getPurchaseQuotation (listener: EventResponseListener<Double>)
    fun getCoinInitials () : String
}