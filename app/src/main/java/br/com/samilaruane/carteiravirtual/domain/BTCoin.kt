package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.repository.remote.ServiceManager
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BTCoin : Coin, EventResponseListener<MercadoBitcoinResponse> {

    lateinit var listener : EventResponseListener<Double>
    var isSale = false
    var isBuy = false

    override fun getSalePrice(listener: EventResponseListener<Double>) {
        ServiceManager().getBitcoinQuotation(this)
        isSale = true
        this.listener = listener
    }

    override fun getPurchaseQuotation(listener: EventResponseListener<Double>) {
        ServiceManager().getBitcoinQuotation(this)
        isBuy = true
        this.listener = listener
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BITCOIN_ACCOUNT
    }

    override fun onSuccess(obj: MercadoBitcoinResponse) {
        if(isSale) listener.onSuccess(obj.ticker.sell)
        else if(isBuy) listener.onSuccess(obj.ticker.sell)

        isSale = false
        isBuy = false
    }

    override fun onError(errorMessage: String) {
        listener.onError(errorMessage)
    }
}