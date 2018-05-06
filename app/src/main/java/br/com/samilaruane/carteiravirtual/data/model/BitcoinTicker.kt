package br.com.samilaruane.carteiravirtual.data.model

/**
 * Created by samila on 17/03/18.
 */
data class BitcoinTicker(val high : Double,
                         val low : Double,
                         val vol : Double,
                         val last : Double,
                         val buy : Double,
                         val sell : Double,
                         val date : Long)