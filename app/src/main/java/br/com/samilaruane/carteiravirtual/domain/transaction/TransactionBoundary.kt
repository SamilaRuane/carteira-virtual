package br.com.samilaruane.carteiravirtual.domain.transaction

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction

interface TransactionBoundary {

    fun get (userId : Long) : List <Transaction>

    fun save (sourceAccount: Account, destinationAccount: Account,
              amount: Double, type: String, userId : Long) : Boolean

    fun process (operationType: String, source: Account?,
                 destination: Account?, amount: Double) : Boolean
}
