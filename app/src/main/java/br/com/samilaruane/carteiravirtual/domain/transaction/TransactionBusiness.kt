package br.com.samilaruane.carteiravirtual.domain.transaction

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import java.util.*
import javax.inject.Inject

class TransactionBusiness @Inject constructor(val gateway: TransactionGateway) : TransactionBoundary {

    override fun get(userId: Long): List<Transaction> = gateway.get(userId)

    override fun save(sourceAccount: Account, destinationAccount: Account, amount: Double, type: String, userId : Long): Boolean {
        val transaction = Transaction(Date().time,
                type,
                amount,
                sourceAccount.getCoin().name,
                destinationAccount.getCoin().name,
                userId)
             return  gateway.create(transaction)
    }

    override fun process(operationType: String, source: Account?, destination: Account?, amount: Double): Boolean {
       var result = false
        when (operationType) {
            BaseConstants.BUY -> {
                result = buy(source!!, destination!!, amount)
            }
            BaseConstants.SELL -> {
                result = sell(source!!, destination!!, amount)
            }
            BaseConstants.TRADE -> {
                result = trade(source!!, destination!!, amount)
            }
        }
        return result
    }

    private fun sell(sourceAccount: Account, destinationAccount: Account, amount: Double) : Boolean {
        return try {
            var saleValue = sourceAccount.getCoin().salePrice
            saleValue = saleValue.times(amount)
            sourceAccount.withdraw(amount)
            destinationAccount.deposit(saleValue)
            true
        } catch (e: InsufficientBalanceException) {
            false
        }
    }


    private fun buy(sourceAccount: Account, destinationAccount: Account, amount: Double) : Boolean {
        return try {
            var purchaseValue = destinationAccount.getCoin().purchaseQuotation
            purchaseValue = purchaseValue.times(amount)
            sourceAccount.withdraw(purchaseValue)
            destinationAccount.deposit(amount)
            true
        } catch (e: InsufficientBalanceException) {
            false
        }
    }

    private fun trade(sourceAccount: Account, destinationAccount: Account, amount: Double) : Boolean {
        return try {
            var saleValue = sourceAccount.getCoin().salePrice
            saleValue = saleValue.times(amount)
            val tradeValue = saleValue / destinationAccount.getCoin().purchaseQuotation

            sourceAccount.withdraw(amount)
            destinationAccount.deposit(tradeValue)
            true
        } catch (e: InsufficientBalanceException) {
            false
        }
    }

}