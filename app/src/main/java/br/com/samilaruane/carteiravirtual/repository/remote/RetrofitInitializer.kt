package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.utils.constants.EndpointConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by samila on 23/12/17.
 */
class RetrofitInitializer {

    fun getMercadoBitcoinService () : MercadoBitcoinService?{
        val retrofit : Retrofit? = init(EndpointConstants.BASE.URL_API_MERCADO_BITCOIN)
        return retrofit?.create(MercadoBitcoinService :: class.java)
    }

    fun getBancoCentralService () : BancoCentralService?{
        val retrofit = init(EndpointConstants.BASE.URL_API_BANCO_CENTRAL)
        return retrofit?.create(BancoCentralService :: class.java)
    }

    private fun init(url : String) : Retrofit? = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

}