package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.BitcoinTicker
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BTCoin : Coin, EventResponseListener<BitcoinTicker> {

    var saleResult: Double = 0.0
    var purchaseResult: Double = 0.0

    override fun getSalePrice(): Double {
        return saleResult
    }

    override fun getPurchaseQuotation(): Double {
        return purchaseResult
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BITCOIN_ACCOUNT
    }

    override fun onSuccess(obj: BitcoinTicker) {
        saleResult = obj.sell
        purchaseResult = obj.buy
    }

    override fun onError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}