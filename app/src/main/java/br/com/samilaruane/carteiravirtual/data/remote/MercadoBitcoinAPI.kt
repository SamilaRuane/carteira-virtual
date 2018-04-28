package br.com.samilaruane.carteiravirtual.data.remote

import br.com.samilaruane.carteiravirtual.data.model.MercadoBitcoinResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by samila on 23/12/17.
 */
interface MercadoBitcoinAPI {

    @GET("ticker")
    fun get () : Call<MercadoBitcoinResponse>
}