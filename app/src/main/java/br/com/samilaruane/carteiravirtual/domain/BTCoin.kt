package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.remote.Service
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 18/12/17.
 */
@Singleton
class BTCoin : Coin, EventResponseListener<MercadoBitcoinResponse> {

    lateinit var mListener: EventResponseListener<String>

    val service: Service<MercadoBitcoinResponse>
    val preferences : SharedPreferencesHelper

    @Inject
    constructor(service: Service<MercadoBitcoinResponse>, preferences : SharedPreferencesHelper) {
        this.service = service
        this.preferences = preferences
    }


    override fun loadCoin(listener: EventResponseListener<String>) {
        service.getCoinQuotation(this)
        mListener = listener
    }

    override fun getSalePrice(): Double = JSONObject(preferences
            .getBitcoinQuotation())
            .get(BaseConstants.SALE_PRICE)
            .toString()
            .toDouble()

    override fun getPurchaseQuotation(): Double = JSONObject(preferences
            .getBitcoinQuotation())
            .get(BaseConstants.PURCHASE_QUOTATION)
            .toString()
            .toDouble()

    override fun getCoinInitials(): String = BaseConstants.BITCOIN_ACCOUNT


    override fun onSuccess(obj: MercadoBitcoinResponse) {
        val map = HashMap<String,String> ()
        map.put (BaseConstants.SALE_PRICE,obj.ticker.sell.toString())
        map.put (BaseConstants.PURCHASE_QUOTATION,obj.ticker.buy.toString())
        preferences.setBitcoinQuotation(JSONObject(map).toString())
        mListener.onSuccess("")
    }

    override fun onError(errorMessage: String) {
        mListener.onError(errorMessage)
    }
}