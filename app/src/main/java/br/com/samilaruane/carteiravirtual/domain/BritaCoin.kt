package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 18/12/17.
 */
class BritaCoin : Coin {
    override fun getSalePrice(): Double {
        //TODO checar cotação retornada pela API
        return 1.0
    }

    override fun getPurchaseQuotation(): Double {
        //TODO checar cotação retornada pela API
        return 1.0
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BRITA_ACCOUNT
    }
}