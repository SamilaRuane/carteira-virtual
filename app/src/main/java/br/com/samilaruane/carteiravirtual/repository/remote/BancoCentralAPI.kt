package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by samila on 23/12/17.
 */
interface BancoCentralAPI {

    /*
    * The URL had to be encoded by hand, because for some reason retrofit wasn't converting de @
    * */

    @GET("CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)")
    fun get (@Query("%40moeda", encoded = true) moeda : String, @Query("%40dataCotacao", encoded = true) dataCotacao : String, @Query("%24format", encoded = true) format : String) : Call<BancoCentralResponse>
}