package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.extension.*
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by samila on 25/03/18.
 */
class BritaService : Service<BancoCentralResponse> {
    override fun getCoinQuotation(listener: EventResponseListener<BancoCentralResponse>) {
        val calendar = Calendar.getInstance()
        calendar.addDays(-1)

        if (!calendar.isWorkingDay()) {
            if(calendar.isSaturday()) calendar.addDays(-1)
            else if (calendar.isSunday()) calendar.addDays(-2)
        }
        val service = RetrofitInitializer()?.getBancoCentralService()?.get("%27USD%27", "%27${calendar.formatter("MM-dd-yyyy")}%27", "json")
        service?.enqueue(object : Callback<BancoCentralResponse> {
            override fun onResponse(call: Call<BancoCentralResponse>?, response: Response<BancoCentralResponse>?) {
                response?.let {
                    if (it.isSuccessful && it.body() != null && it.body()?.value != null && it.body()?.value!!.isNotEmpty()) {
                        listener?.onSuccess(it.body()!!)
                    } else {
                        listener?.onError(BaseConstants.MESSAGES.GENERIC_ERROR)
                    }
                }
            }

            override fun onFailure(call: Call<BancoCentralResponse>?, t: Throwable?) {
                listener?.onError(BaseConstants.MESSAGES.GENERIC_ERROR)
            }
        })
    }
}