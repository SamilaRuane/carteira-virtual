package br.com.samilaruane.carteiravirtual.domain

import android.content.Context
import br.com.samilaruane.carteiravirtual.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.repository.db.TransactionRepository
import br.com.samilaruane.carteiravirtual.repository.db.TransactionRepositoryImpl
import java.util.*

/**
 * Created by samila on 07/01/18.
 */
class TransactionBusiness(val ctx : Context) {

    var sourceAccount : Account? = null
    var destinationAccount : Account? = null

    var transactionRepository: TransactionRepository = TransactionRepositoryImpl.getInstance(ctx)

    fun manageTransaction (operationType: String, sourceAccount: String, destinationAccount: String, amount: Double, user : User){

        val userBussiness = UserBusiness(ctx)
        this.sourceAccount = userBussiness.getAccountByCoin(sourceAccount, user)
        this.destinationAccount = userBussiness.getAccountByCoin(destinationAccount, user)

        if(sourceAccount != null && destinationAccount != null) {
            when (operationType) {
                BaseConstants.SELL -> {
                    sell(this.sourceAccount!!, this.destinationAccount!!, amount)
                }
                BaseConstants.BUY -> {
                    buy(this.sourceAccount!!, this.destinationAccount!!, amount)
                }
                BaseConstants.TRADE -> {
                    trade(this.sourceAccount!!, this.destinationAccount!!, amount)
                }

            }
        }
    }

    private fun sell(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        val sourceAccountCoin = sourceAccount.getCoin()
        if (sourceAccount.withdraw(amount))
            destinationAccount.deposit(amount * sourceAccountCoin.getSalePrice())
        save(BaseConstants.SELL, amount, sourceAccount, destinationAccount)

    }

    private fun buy(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        val destinationAccountCoin = destinationAccount.getCoin()

        if (sourceAccount.withdraw(amount * destinationAccountCoin.getPurchaseQuotation()))
            destinationAccount.deposit(amount)
        save(BaseConstants.BUY, amount, sourceAccount, destinationAccount)
    }

    private fun trade(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        val coinSourceAccount = sourceAccount.getCoin()
        val coinDestinationAccount = destinationAccount.getCoin()
        save(BaseConstants.TRADE, amount, sourceAccount, destinationAccount)
    }
    private fun save (type : String, amount : Double, sourceAccount: Account, destinationAccount: Account){
        val transaction = Transaction (Date().time, type, amount,
                sourceAccount,
                destinationAccount)
        transactionRepository.insert(transaction)
    }
}