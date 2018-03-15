package br.com.samilaruane.carteiravirtual.repository.db

import br.com.samilaruane.carteiravirtual.domain.Transaction

/**
 * Created by samila on 07/01/18.
 */
interface TransactionRepository {

    fun insert (transaction: Transaction) : Int
    fun select (userId : String) : List <Transaction>
}