package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import br.com.samilaruane.carteiravirtual.repository.db.Repository
import br.com.samilaruane.carteiravirtual.repository.db.SearchFilter
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OperationType
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import java.util.*
import javax.inject.Inject

/**
 * Created by samila on 07/01/18.
 */

class TransactionBusiness @Inject constructor(private val userBusiness: UserBusiness, private val transactionRepository: Repository<Transaction>, private val brita: BritaCoin, private val bitcoin: BTCoin) : EventResponseListener<String> {

    private lateinit var listener: EventResponseListener<String>
    private var baseAccount: Account? = null


    fun callServices (listener: EventResponseListener<String>){
        brita.loadCoin(this)
        bitcoin.loadCoin(this)
        this.listener = listener
    }

    fun process(operationType: OperationType, sourceCoin: String,
                destinationCoin: String, amount: Double, user : User) {

            baseAccount = userBusiness.getAccountByCoin(BaseConstants.BRL_ACCOUNT, user)
        val bitcoinAccount = userBusiness.getAccountByCoin(BaseConstants.BITCOIN_ACCOUNT, user)
        val britaAccount = userBusiness.getAccountByCoin(BaseConstants.BRITA_ACCOUNT, user)
        var sourceAccount: Account? = null
        var destinationAccount: Account? = null

        when (sourceCoin) {
            BaseConstants.BITCOIN_ACCOUNT -> sourceAccount = bitcoinAccount!!
            BaseConstants.BRL_ACCOUNT -> sourceAccount = baseAccount!!
            BaseConstants.BRITA_ACCOUNT -> sourceAccount = britaAccount!!
        }

        when (destinationCoin) {
            BaseConstants.BITCOIN_ACCOUNT -> destinationAccount = bitcoinAccount!!
            BaseConstants.BRL_ACCOUNT -> destinationAccount = baseAccount!!
            BaseConstants.BRITA_ACCOUNT -> destinationAccount = britaAccount!!
        }

        when (operationType) {
            OperationType.BUY -> {
                buy(sourceAccount!!, destinationAccount!!, amount)
            }
            OperationType.SALE -> {
                sell(sourceAccount!!, destinationAccount!!, amount)
            }
            OperationType.TRADE -> {
                trade(sourceAccount!!, destinationAccount!!, amount)
            }
        }

    }

    /*
    * @param sourceAccount : Will take money from
    * @param destinationAccount : Will put money in
    * */

    private fun sell(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        try {
            val saleValue = sourceAccount.getCoin().getSalePrice()

                saleValue.times(amount)
            sourceAccount.withdraw(amount)
            destinationAccount.deposit(saleValue)
                saveTransaction(sourceAccount, destinationAccount, amount, BaseConstants.SELL)
                listener.onSuccess(BaseConstants.MESSAGES.SUCCESS_ON_TASK)
        } catch (e: InsufficientBalanceException) {
            if (e.message != null) listener.onError(e.message)
            return
        }
    }

    private fun buy(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        try {
            val purchaseValue = destinationAccount.getCoin().getPurchaseQuotation()
            purchaseValue.times(amount)
            sourceAccount.withdraw(purchaseValue)
            destinationAccount.deposit(amount)
            saveTransaction(sourceAccount, destinationAccount, amount, BaseConstants.BUY)
            listener.onSuccess(BaseConstants.MESSAGES.SUCCESS_ON_TASK)
        } catch (e: InsufficientBalanceException) {
            if (e.message != null) listener.onError(e.message)
            return
        }
    }

    private fun trade(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        try {
            val saleValue = sourceAccount.getCoin().getSalePrice()
            saleValue.times(amount)
            val tradeValue = saleValue / destinationAccount.getCoin().getPurchaseQuotation()

            sourceAccount.withdraw(amount)
            destinationAccount.deposit(tradeValue)

            saveTransaction(sourceAccount, destinationAccount, amount, BaseConstants.TRADE)
            listener.onSuccess(BaseConstants.MESSAGES.SUCCESS_ON_TASK)
        }catch (e : InsufficientBalanceException){
            if(e.message != null)
                listener.onError(e.message)
                return
        }
    }

    private fun saveTransaction(sourceAccount: Account, destinationAccount: Account, amount: Double, type: String) {

        userBusiness.updateAccount(sourceAccount)
        userBusiness.updateAccount(destinationAccount)

        val transaction = Transaction(Date().time,
                type,
                amount,
                sourceAccount.getCoin().getCoinInitials(),
                destinationAccount.getCoin().getCoinInitials())

        transactionRepository.create(transaction)
    }

    fun getTransactions(): List <Transaction> = transactionRepository
            .select(SearchFilter
            .getAll(DatabaseConstants.TRANSACTION.TABLE_NAME))

    override fun onSuccess(obj: String) = listener.onSuccess(obj)

    override fun onError(errorMessage: String) = listener.onError(errorMessage)

}