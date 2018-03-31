package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BRLCoin : Coin {

    override fun getCoinInitials(): String {
        return BaseConstants.BRL_ACCOUNT
    }

    override fun loadCoin(listener: EventResponseListener<String>) { }

    override fun getSalePrice(): Double = 1.0


    override fun getPurchaseQuotation(): Double = 1.0

}