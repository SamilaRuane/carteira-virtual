package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.repository.remote.Service
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 18/12/17.
 */
@Singleton
class BTCoin : Coin, EventResponseListener<MercadoBitcoinResponse> {

    lateinit var listener: EventResponseListener<Double>
    var isSale = false
    var isBuy = false

    val service: Service<MercadoBitcoinResponse>

    @Inject
    constructor(service: Service<MercadoBitcoinResponse>) {
        this.service = service
    }


    override fun getSalePrice(listener: EventResponseListener<Double>) {
        service.getCoinQuotation(this)
        isSale = true
        this.listener = listener
    }

    override fun getPurchaseQuotation(listener: EventResponseListener<Double>) {
        service.getCoinQuotation(this)
        isBuy = true
        this.listener = listener
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BITCOIN_ACCOUNT
    }

    override fun onSuccess(obj: MercadoBitcoinResponse) {
        if (isSale) listener.onSuccess(obj.ticker.sell)
        else if (isBuy) listener.onSuccess(obj.ticker.sell)

        isSale = false
        isBuy = false
    }

    override fun onError(errorMessage: String) {
        listener.onError(errorMessage)
    }
}