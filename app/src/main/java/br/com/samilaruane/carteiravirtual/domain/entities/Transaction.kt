package br.com.samilaruane.carteiravirtual.domain.entities

import br.com.samilaruane.carteiravirtual.extension.formatter
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import java.util.*


/**
 * Created by samila on 21/12/17.
 */
data class Transaction(val date: Long,
                       val transactionType: String,
                       val amount: Double,
                       val sourceCoin: String,
                       val destinationCoin: String,
                       val userId : Long) {


    override fun toString(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val transactionSummary: String
        transactionSummary = when (transactionType) {
            BaseConstants.BUY -> "${calendar.formatter("dd/MM/yyyy")} - $transactionType de $amount $destinationCoin"
            BaseConstants.SELL -> "${calendar.formatter("dd/MM/yyyy")} - $transactionType de $amount $sourceCoin"
            else -> "${calendar.formatter("dd/MM/yyyy")} - $transactionType de $amount $sourceCoin por $destinationCoin"
        }

        return transactionSummary
    }
}