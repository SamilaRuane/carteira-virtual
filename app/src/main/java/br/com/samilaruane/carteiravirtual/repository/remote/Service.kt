package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.utils.EventResponseListener

/**
 * Created by samila on 25/03/18.
 */
interface Service<T> {
    fun getCoinQuotation(listener : EventResponseListener<T>, date:String)
}