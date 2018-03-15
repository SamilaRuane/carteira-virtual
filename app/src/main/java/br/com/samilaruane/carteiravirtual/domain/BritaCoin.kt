package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.DollarExchangeRate
import br.com.samilaruane.carteiravirtual.repository.remote.ServiceManager
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BritaCoin : Coin, EventResponseListener<DollarExchangeRate> {

    var saleResult: Double = 0.0
    var purchaseResult: Double = 0.0

    constructor() {
        ServiceManager().getBritaQuotation(this)
    }


    override fun getSalePrice(): Double {
        return saleResult
    }

    override fun getPurchaseQuotation(): Double {
        return purchaseResult
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BRITA_ACCOUNT
    }

    override fun onSuccess(obj: DollarExchangeRate) {
        saleResult = obj.cotacaoVenda
        purchaseResult = obj.cotacaoCompra
    }

    override fun onError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}