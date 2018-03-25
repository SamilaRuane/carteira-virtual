package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.extension.*
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by samila on 15/03/18.
 */
class ServiceManager {

    private val bitcoinMarket : MercadoBitcoinService?
    private val centralBank : BancoCentralService?
    private val ERROR_MESSAGE = "Caro cliente, no momento não foi possível recuperar as informações. Tente Novamente em instantes"

    constructor(){
        bitcoinMarket = RetrofitInitializer()?.getMercadoBitcoinService()
        centralBank = RetrofitInitializer()?.getBancoCentralService()
    }


    fun getBitcoinQuotation (listener : EventResponseListener<MercadoBitcoinResponse>) {
       val service = bitcoinMarket?.get()
        service?.enqueue(object : Callback<MercadoBitcoinResponse> {
            override fun onResponse(call: Call<MercadoBitcoinResponse>?, response: Response<MercadoBitcoinResponse>?) {
                if(response?.isSuccessful!!){
                    listener.onSuccess(response?.body()!!)
                }else {
                    listener.onError(ERROR_MESSAGE)
                }
            }

            override fun onFailure(call: Call<MercadoBitcoinResponse>?, t: Throwable?) {
                //TODO verify if the failure was caused by internet connection
                listener.onError(ERROR_MESSAGE)
            }
        })
    }

    fun getBritaQuotation (listener : EventResponseListener<BancoCentralResponse>?) {
        val calendar = Calendar.getInstance()

        if (!calendar.isWorkingDay()) {
            if(calendar.isSaturday()) calendar.addDays(-1)
            else if (calendar.isSunday()) calendar.addDays(-2)
        }
            val service = centralBank?.get("%27USD%27", "%27${calendar.formatter("MM-dd-yyyy")}%27", "json")
            service?.enqueue(object : Callback<BancoCentralResponse> {
                override fun onResponse(call: Call<BancoCentralResponse>?, response: Response<BancoCentralResponse>?) {
                    response?.let {
                        if (it.isSuccessful && it.body() != null && it.body()?.value != null && it.body()?.value!!.isNotEmpty()) {
                            listener?.onSuccess(it.body()!!)
                        } else {
                            listener?.onError(ERROR_MESSAGE)
                        }
                    }
                }

                override fun onFailure(call: Call<BancoCentralResponse>?, t: Throwable?) {
                    //TODO verify if the failure was caused by internet connection
                    listener?.onError(ERROR_MESSAGE)

                }
            })
        }

}