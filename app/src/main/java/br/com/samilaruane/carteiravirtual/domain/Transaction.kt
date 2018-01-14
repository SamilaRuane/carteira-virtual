package br.com.samilaruane.carteiravirtual.domain

/**
 * Created by samila on 21/12/17.
 */
data class Transaction(val date : Long,
                       val transactionType : String,
                       val amount : Double,
                       val sourceAccount : Account,
                       val destinationAccount : Account){

    fun save(){

    }
    override fun toString(): String {
        return ""
    }
}