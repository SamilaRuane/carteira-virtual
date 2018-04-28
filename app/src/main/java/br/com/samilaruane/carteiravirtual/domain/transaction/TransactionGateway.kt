package br.com.samilaruane.carteiravirtual.domain.transaction

import br.com.samilaruane.carteiravirtual.domain.entities.Transaction

interface TransactionGateway {
    fun create (transaction : Transaction) : Boolean
    fun edit (transaction: Transaction)
    fun get (id : Long) : List<Transaction>
}