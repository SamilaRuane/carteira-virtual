package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.extension.*
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.remote.Service
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.json.JSONObject
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 18/12/17.
 */
@Singleton
class BritaCoin : Coin, EventResponseListener<BancoCentralResponse> {

    private lateinit var mListener : EventResponseListener<String>
    private lateinit var mCalendar : Calendar

    val service : Service<BancoCentralResponse>
    val preferences : SharedPreferencesHelper



    @Inject
    constructor(service: Service<BancoCentralResponse>, preferences : SharedPreferencesHelper) {
        this.service = service
        this.preferences = preferences
        mCalendar = Calendar.getInstance()
    }


    override fun loadCoin(listener: EventResponseListener<String>) {

        mCalendar.addDays(-1)

        if (!mCalendar.isWorkingDay()) {
            if(mCalendar.isSaturday()) mCalendar.addDays(-1)
            else if (mCalendar.isSunday()) mCalendar.addDays(-2)
        }


        service.getCoinQuotation(this, mCalendar.formatter("MM-dd-yyyy"))

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
        if(obj.value.isEmpty()){
            mCalendar.addDays(-1)
            service.getCoinQuotation(this, mCalendar.formatter("MM-dd-yyyy"))
        }else {
            val map = HashMap<String, String>()
            if (obj.value != null && !obj.value.isEmpty()) {
                map.put(BaseConstants.SALE_PRICE, obj.value[0].cotacaoVenda.toString())
                map.put(BaseConstants.PURCHASE_QUOTATION, obj.value[0].cotacaoCompra.toString())
                preferences.setBritaQuotation(JSONObject(map).toString())
            }
            mListener.onSuccess("")
        }
    }

    override fun onError(errorMessage: String) {
        mListener.onError(errorMessage)
    }
}
