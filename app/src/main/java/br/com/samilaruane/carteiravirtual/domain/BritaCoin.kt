package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.domain.entities.DollarExchangeRate
import br.com.samilaruane.carteiravirtual.repository.remote.ServiceManager
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BritaCoin : Coin, EventResponseListener<BancoCentralResponse> {

    lateinit var listener : EventResponseListener<Double>
    var isSale = false
    var isBuy = false

    override fun getSalePrice(listener: EventResponseListener <Double>) {
        ServiceManager().getBritaQuotation(this)
        isSale = true
        this.listener = listener
    }

    override fun getPurchaseQuotation(listener: EventResponseListener <Double>) {
        ServiceManager().getBritaQuotation(this)
        isBuy = true
        this.listener = listener
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BRITA_ACCOUNT
    }

    override fun onSuccess(obj: BancoCentralResponse) {
        if(obj.value != null && !obj.value.isEmpty())
            if(isSale) listener.onSuccess(obj.value[0].cotacaoVenda)
            else if(isBuy) listener.onSuccess(obj.value[0].cotacaoCompra)

        isSale = false
        isBuy = false
    }

    override fun onError(errorMessage: String) {
        listener.onError(errorMessage)
    }
}