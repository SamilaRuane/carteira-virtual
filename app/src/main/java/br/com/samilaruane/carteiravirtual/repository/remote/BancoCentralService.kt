package br.com.samilaruane.carteiravirtual.repository.remote

import br.com.samilaruane.carteiravirtual.entities.DollarExchangeRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by samila on 23/12/17.
 */
interface BancoCentralService {

    @GET("CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)")
    fun get (@Query("[moeda]") moeda : String, @Query("dataCotacao")data : String) : Call<DollarExchangeRate>
}