package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.domain.entities.BitcoinTicker
import br.com.samilaruane.carteiravirtual.domain.entities.DollarExchangeRate
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
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


    fun getBitcoinQuotation (listener : EventResponseListener<BitcoinTicker>) {
       val service = bitcoinMarket?.get()
        service?.enqueue(object : Callback<BitcoinTicker> {
            override fun onResponse(call: Call<BitcoinTicker>?, response: Response<BitcoinTicker>?) {
                if(response?.isSuccessful!!){
                    listener.onSuccess(response?.body()!!)
                }
                listener.onError(ERROR_MESSAGE)
            }

            override fun onFailure(call: Call<BitcoinTicker>?, t: Throwable?) {
                //TODO verify if the failure was caused by internet connection
                listener.onError(ERROR_MESSAGE)
            }
        })
    }

    fun getBritaQuotation (listener : EventResponseListener<DollarExchangeRate>?) {
        val date = Date ()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val service = centralBank?.get("US", sdf.format(date))
        service?.enqueue(object : Callback<DollarExchangeRate> {
            override fun onResponse(call: Call<DollarExchangeRate>?, response: Response<DollarExchangeRate>?) {
                if(response?.isSuccessful!!){
                    listener?.onSuccess(response?.body()!!)
                }
                listener?.onError(ERROR_MESSAGE)
            }

            override fun onFailure(call: Call<DollarExchangeRate>?, t: Throwable?) {
                //TODO verify if the failure was caused by internet connection
                listener?.onError(ERROR_MESSAGE)

            }
        })
    }
}