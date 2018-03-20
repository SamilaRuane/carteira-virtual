package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by samila on 21/12/17.
 */
data class Transaction(val date : Long,
                       val transactionType : String,
                       val amount : Double,
                       val sourceCoin : String,
                       val destinationCoin : String){

    private fun dateformatter (date : Long) : String{
        val sdf = SimpleDateFormat ("dd/MM/yyyy")
        val date = Date(date)

        return sdf?.format(date)
    }

     override fun toString(): String {
        return "${dateformatter(date)} - $transactionType de $amount $destinationCoin por $sourceCoin"
    }
}