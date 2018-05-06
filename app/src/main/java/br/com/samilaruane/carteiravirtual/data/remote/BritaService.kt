package br.com.samilaruane.carteiravirtual.data.remote

import br.com.samilaruane.carteiravirtual.data.model.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by samila on 25/03/18.
 */
class BritaService  {
    companion object {
        fun getCoinQuotation(listener: ServiceListener.Brita, date: String) {
            val service = RetrofitInitializer().getBancoCentralService()?.get("%27USD%27", "%27$date%27", "json")
            service?.enqueue(object : Callback<BancoCentralResponse> {
                override fun onResponse(call: Call<BancoCentralResponse>?, response: Response<BancoCentralResponse>?) {
                    response?.let {
                        if (it.isSuccessful && it.body() != null && it.body()?.value != null) {
                            listener.onSuccess(it.body()!!)
                        } else {
                            listener.onError(BaseConstants.MESSAGES.GENERIC_ERROR)
                        }
                    }
                }

                override fun onFailure(call: Call<BancoCentralResponse>?, t: Throwable?) {
                    listener.onError(BaseConstants.MESSAGES.GENERIC_ERROR)
                }
            })
        }
    }
}