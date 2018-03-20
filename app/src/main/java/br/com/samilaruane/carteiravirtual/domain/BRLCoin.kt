package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BRLCoin : Coin {
    override fun getSalePrice(listener: EventResponseListener<Double>) {

    }

    override fun getPurchaseQuotation(listener: EventResponseListener<Double>) {

    }

    override fun getCoinInitials(): String {
        return BaseConstants.BRL_ACCOUNT
    }
}