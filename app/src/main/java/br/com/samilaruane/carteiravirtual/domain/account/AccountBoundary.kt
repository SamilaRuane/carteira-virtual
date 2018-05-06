package br.com.samilaruane.carteiravirtual.domain.account

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Coin
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener

interface AccountBoundary {
    fun createAccountsTo(id : Long) : Boolean
    fun edit (account: Account) : Boolean
    fun getFrom (userId : Long) : List<Account>
    fun callServices (listener : EventResponseListener<String>)
    fun getQuotation(coinName : String): Coin
    fun getAccount (coin : String, accounts : List <Account>) : Account?
}