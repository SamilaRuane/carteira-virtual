package br.com.samilaruane.carteiravirtual.domain.entities

import br.com.samilaruane.carteiravirtual.domain.Coin
import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import java.io.Serializable

/**
 * Created by samila on 18/12/17.
 */
class Account(private val userId : Long, private val coin : Coin, private var accountBalance : Double = 0.0) : Serializable {

    private var id : Long = 0


     fun deposit(amount: Double) : Boolean {
        if (amount < 0)  return false
        this.accountBalance += amount
        return true
    }

    fun withdraw(amount: Double) : Boolean {
        //TODO Tratar em outro lugar
        try {
        if (amount > this.accountBalance) throw  InsufficientBalanceException("Saldo Insuficiente")
            this.accountBalance -= amount
            return true
        }catch(e : InsufficientBalanceException){
        return false
        }
    }

    fun getAccountBalance(): Double {
        return this.accountBalance
    }

    fun getCoin() : Coin {
        return this.coin
    }

    fun getUserId () : Long {
        return this.userId
    }

    fun getId(): Long {
        return this.id
    }
}