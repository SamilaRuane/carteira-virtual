package br.com.samilaruane.carteiravirtual.domain.account

import br.com.samilaruane.carteiravirtual.domain.entities.Account


interface AccountGateway {
    fun create (account : Account) : Long
    fun edit (account: Account) : Boolean
    fun get (id : Long) : List <Account>
    fun setBitcoinQuotation (quotation : String)
    fun setBritaQuotation (quotation: String)
    fun getBitcoinQuotation () : String
    fun getBritaQuotation () : String

}