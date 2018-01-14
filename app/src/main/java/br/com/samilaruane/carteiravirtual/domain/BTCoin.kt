package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BTCoin : Coin {
    override fun getSalePrice(): Double {
        return 1.0
    }

    override fun getPurchaseQuotation(): Double {
        return 1.0
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BITCOIN_ACCOUNT
    }
}