package br.com.samilaruane.carteiravirtual.repository.db

import br.com.samilaruane.carteiravirtual.domain.entities.Account


/**
 * Created by samila on 07/01/18.
 */
interface AccountRepository {
    fun insert (account : Account) : Int
    fun select (userId : String) : MutableList<Account>
}