package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.extension.formatter
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import java.util.*


/**
 * Created by samila on 21/12/17.
 */
data class Transaction(val date : Long,
                       val transactionType : String,
                       val amount : Double,
                       val sourceCoin : String,
                       val destinationCoin : String){


     override fun toString(): String {
         val calendar = Calendar.getInstance()
         calendar.timeInMillis = date
        var transactionSummary : String
             if (transactionType.equals(BaseConstants.SELL) || transactionType.equals(BaseConstants.BUY))
                transactionSummary = "${calendar.formatter ("dd/MM/yyyy")} - $transactionType de $amount $destinationCoin"
            else
                 transactionSummary = "${calendar.formatter ("dd/MM/yyyy")} - $transactionType de $amount $destinationCoin por $sourceCoin"

         return transactionSummary
     }
}