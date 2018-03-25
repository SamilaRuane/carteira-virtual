package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import br.com.samilaruane.carteiravirtual.repository.db.Repository
import br.com.samilaruane.carteiravirtual.repository.db.SearchFilter
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OperationType
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import java.util.*
import javax.inject.Inject

/**
 * Created by samila on 07/01/18.
 */
//TODO resolver problemas com a troca de moedas, as conversões de uma moeda pra outra está errada
class TransactionBusiness : EventResponseListener<Double> {

    private var baseAccount: Account? = null
    private var  bitcoinAccount: Account? = null
    private var  britaAccount: Account? = null
    var sourceAccount: Account? = null
    var destinationAccount: Account? = null
    var amount: Double = 0.0
    lateinit var operationType: OperationType

    private val userBussiness: UserBusiness

    private val transactionRepository: Repository<Transaction>

    private var purchaseValue: Double = 0.0
    private var saleValue: Double = 0.0
    private var tradeValue: Double = 0.0

    private lateinit var listener: EventResponseListener<String>


    @Inject
    constructor(userBusiness: UserBusiness, transactionRepository: Repository<Transaction>) {
        this.userBussiness = userBusiness
        this.transactionRepository = transactionRepository
    }


    /*
    * O que acontece em uma operação de compra de bitcoin?
    *   1. Consulta cotação de compra da moeda
    *   2. Multiplica pela quantidade que deseja comprar
    *   3. Desconta da conta base o valor
    * */

    //TODO verificar as asserções nulas

    fun process(operationType: OperationType, sourceCoin: String,
                destinationCoin: String, amount: Double, user : User, listener: EventResponseListener<String>) {

        baseAccount = userBussiness.getAccountByCoin(BaseConstants.BRL_ACCOUNT, user)
        bitcoinAccount = userBussiness.getAccountByCoin(BaseConstants.BITCOIN_ACCOUNT, user)
        britaAccount = userBussiness.getAccountByCoin(BaseConstants.BRITA_ACCOUNT, user)

        this.listener = listener

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

        this.amount = amount
        this.operationType = operationType

        when (operationType) {
            OperationType.BUY -> {
                destinationAccount?.getCoin()?.getPurchaseQuotation(this)
            }
            OperationType.SALE -> {
                sourceAccount?.getCoin()?.getSalePrice(this)
            }
            OperationType.TRADE -> {
                destinationAccount?.getCoin()?.getPurchaseQuotation(this)
                sourceAccount?.getCoin()?.getSalePrice(this)
            }
        }

    }
    /*
    * @param sourceAccount : Will take money from
    * @param destinationAccount : Will put money in
    * */

    private fun sell(sourceAccount: Account, destinationAccount: Account, amount: Double) {

        if (operationType.equals(OperationType.TRADE)) tradeValue.times(amount)

        try {
            saleValue.times(amount)
            sourceAccount?.withdraw(amount)
        } catch (e: InsufficientBalanceException) {
            if (e.message != null) listener.onError(e.message)
            return
        }

        destinationAccount?.deposit(saleValue)
        saleValue = 0.0
        saveTransaction(sourceAccount, destinationAccount, amount, BaseConstants.SELL)
        listener.onSuccess("")
    }

    private fun buy(sourceAccount: Account, destinationAccount: Account, amount: Double) {

        try {
            purchaseValue.times(amount)
            sourceAccount.withdraw(purchaseValue)
        } catch (e: InsufficientBalanceException) {
            if (e.message != null) listener.onError(e.message)
            return
        }

        destinationAccount.deposit(amount)
        purchaseValue = 0.0
        saveTransaction(sourceAccount, destinationAccount, amount, BaseConstants.BUY)
        listener.onSuccess("")
    }

    private fun trade(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        sell(sourceAccount, baseAccount!!, amount)
        buy(baseAccount as Account, destinationAccount, tradeValue)

        saveTransaction(sourceAccount, destinationAccount, amount, BaseConstants.TRADE)
    }

    private fun saveTransaction(sourceAccount: Account, destinationAccount: Account, amount: Double, type: String) {

        userBussiness.updateAccount(sourceAccount)
        userBussiness.updateAccount(destinationAccount)

        val transaction = Transaction(Date().time,
                type,
                amount,
                sourceAccount.getCoin().getCoinInitials(),
                destinationAccount.getCoin().getCoinInitials())

        transactionRepository.create(transaction)
    }

    fun getTransactions(): List <Transaction> {
        return transactionRepository.select(SearchFilter.getAll
        (DatabaseConstants.TRANSACTION.TABLE_NAME))
    }

    override fun onSuccess(obj: Double) {

        when (operationType) {
            OperationType.BUY -> {
                purchaseValue = obj
                buy(sourceAccount!!, destinationAccount!!, amount)
            }
            OperationType.SALE -> {
                saleValue = obj
                sell(sourceAccount!!, destinationAccount!!, amount)
            }
            OperationType.TRADE -> {
                trade(sourceAccount!!, destinationAccount!!, amount)
            }
        }
    }

    override fun onError(errorMessage: String) {
        listener.onError(errorMessage)
    }
}