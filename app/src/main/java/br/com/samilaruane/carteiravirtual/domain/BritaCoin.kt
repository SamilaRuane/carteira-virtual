package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
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
class BritaCoin @Inject constructor(val service: Service<BancoCentralResponse>, val preferences: SharedPreferencesHelper) : Coin, EventResponseListener<BancoCentralResponse> {

    private lateinit var mListener : EventResponseListener<String>


    override fun loadCoin(listener: EventResponseListener<String>) {
        service.getCoinQuotation(this)
        mListener = listener
    }

    override fun getCoinInitials(): String {
        return BaseConstants.BRITA_ACCOUNT
    }

    override fun getSalePrice(): Double = JSONObject(preferences.getBritaQuotation())
            .get(BaseConstants.SALE_PRICE)
            .toString()
            .toDouble()


    override fun getPurchaseQuotation(): Double = JSONObject(preferences.getBritaQuotation())
            .get(BaseConstants.PURCHASE_QUOTATION)
            .toString()
            .toDouble()

    override fun onSuccess(obj: BancoCentralResponse) {
        val map = HashMap<String, String>()
            if(obj.value != null && !obj.value.isEmpty()) {
                map.put(BaseConstants.SALE_PRICE,obj.value[0].cotacaoVenda.toString())
                map.put(BaseConstants.PURCHASE_QUOTATION,obj.value[0].cotacaoCompra.toString() )
                preferences.setBritaQuotation(JSONObject(map).toString())
            }
        mListener.onSuccess("")
    }

    override fun onError(errorMessage: String) {
        mListener.onError(errorMessage)
    }
}
