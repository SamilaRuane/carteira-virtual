package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.repository.remote.Service
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 18/12/17.
 */
@Singleton
class BritaCoin : Coin, EventResponseListener<BancoCentralResponse> {

    private lateinit var listener : EventResponseListener<Double>
    private var isSale = false
    private var isBuy = false

    val service : Service<BancoCentralResponse>


    @Inject
    constructor(service: Service<BancoCentralResponse>) {
        this.service = service
    }


    override fun getSalePrice(listener: EventResponseListener <Double>) {
        service.getCoinQuotation(this)
        isSale = true
        this.listener = listener
    }

    override fun getPurchaseQuotation(listener: EventResponseListener <Double>) {
        service.getCoinQuotation(this)
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