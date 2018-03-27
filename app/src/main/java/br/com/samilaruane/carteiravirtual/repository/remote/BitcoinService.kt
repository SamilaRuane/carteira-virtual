package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by samila on 25/03/18.
 */
class BitcoinService : Service <MercadoBitcoinResponse> {
    override fun getCoinQuotation(listener: EventResponseListener<MercadoBitcoinResponse>) {
        val service = RetrofitInitializer()?.getMercadoBitcoinService()?.get()
        service?.enqueue(object : Callback<MercadoBitcoinResponse> {
            override fun onResponse(call: Call<MercadoBitcoinResponse>?, response: Response<MercadoBitcoinResponse>?) {
                if(response?.isSuccessful!!){
                    listener.onSuccess(response?.body()!!)
                }else {
                    listener.onError(BaseConstants.MESSAGES.GENERIC_ERROR)
                }
            }

            override fun onFailure(call: Call<MercadoBitcoinResponse>?, t: Throwable?) {
                //TODO verify if the failure was caused by internet connection
                listener.onError(BaseConstants.MESSAGES.GENERIC_ERROR)
            }
        })
    }
}