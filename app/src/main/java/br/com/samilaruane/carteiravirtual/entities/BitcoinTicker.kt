package br.com.samilaruane.carteiravirtual.entities

/**
 * Created by samila on 23/12/17.
 */
data class BitcoinTicker (val high : Double,
                          val low : Double,
                          val vol : Double,
                          val last : Double,
                          val buy : Double,
                          val sell : Double,
                          val date : Long)

/*TODO ("vê a possibilidade de só pegar os parametros buy, sell e date") */