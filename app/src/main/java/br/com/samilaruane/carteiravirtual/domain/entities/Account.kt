package br.com.samilaruane.carteiravirtual.domain.entities

import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import java.io.Serializable

/**
 * Created by samila on 18/12/17.
 */
class Account(private var id: Long, private val userId: Long, private var coin: Coin, private var accountBalance: Double = 0.0) : Serializable {

    fun deposit(amount: Double): Boolean {
        if (amount < 0) return false
        this.accountBalance += amount
        return true
    }

    fun withdraw(amount: Double): Boolean {
            if(amount < 0 ) return false
            if (amount > this.accountBalance) throw  InsufficientBalanceException("Saldo Insuficiente")
            this.accountBalance -= amount
            return true
    }

    fun getAccountBalance(): Double {
        return this.accountBalance
    }

    fun setCoin (coin : Coin){
        this.coin = coin
    }

    fun getCoin(): Coin {
        return this.coin
    }

    fun getUserId(): Long {
        return this.userId
    }

    fun getId(): Long {
        return this.id
    }
}