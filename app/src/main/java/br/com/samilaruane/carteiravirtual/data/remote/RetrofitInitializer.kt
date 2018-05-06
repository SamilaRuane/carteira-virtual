package br.com.samilaruane.carteiravirtual.data.remote

import br.com.samilaruane.carteiravirtual.BuildConfig
import br.com.samilaruane.carteiravirtual.utils.constants.EndpointConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by samila on 23/12/17.
 */
class RetrofitInitializer {

    fun getMercadoBitcoinService () : MercadoBitcoinAPI?{
        val retrofit : Retrofit? = init(EndpointConstants.BASE.URL_API_MERCADO_BITCOIN)
        return retrofit?.create(MercadoBitcoinAPI:: class.java)
    }

    fun getBancoCentralService () : BancoCentralAPI?{
        val retrofit = init(EndpointConstants.BASE.URL_API_BANCO_CENTRAL)
        return retrofit?.create(BancoCentralAPI:: class.java)
    }

    private fun init(url : String) : Retrofit? {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        if (BuildConfig.DEBUG) {
                okHttpClientBuilder.addInterceptor(logger)
            }

       return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
    }

}