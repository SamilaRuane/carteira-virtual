package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import javax.inject.Inject

class MainInteractor @Inject constructor(val userBusiness : UserBoundary, val transactionBusiness : TransactionBoundary, val accountBusiness : AccountBoundary) : MainContract.Interactor {

    override fun getAccounts(): List<Account> =
            accountBusiness.getFrom(userBusiness.getCurrent().id)

    override fun getTransactions(): List<Transaction> = transactionBusiness.get(userBusiness.getCurrent().id)

    override fun getUser() : User = userBusiness.getCurrent()

    override fun updateUser(user : User): Boolean = userBusiness.update(user)

    override fun loadCoins(listener : EventResponseListener<String>) {
        accountBusiness.callServices(listener)
    }
}
