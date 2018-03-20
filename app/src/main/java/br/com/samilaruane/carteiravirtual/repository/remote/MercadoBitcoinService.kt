package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by samila on 23/12/17.
 */
interface MercadoBitcoinService {

    @GET("ticker")
    fun get () : Call<MercadoBitcoinResponse>
}