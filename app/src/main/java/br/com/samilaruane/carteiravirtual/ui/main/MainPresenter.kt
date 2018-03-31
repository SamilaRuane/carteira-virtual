package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener
import javax.inject.Inject

/**
 * Created by samila on 07/01/18.
 */
class MainPresenter @Inject constructor(var mView: MainContract.View, var mUserBussiness: UserBusiness, var mTransactionBussiness: TransactionBusiness) : MainContract.Presenter, EventResponseListener<String> {

    override fun detachView() {
        mView = null!!
    }

    override fun loadAccounts(listener: OnDatabaseAccessListener<List<Account>>) {
        listener.onSelectSuccess(mUserBussiness.getUserAccounts())
    }

    override fun loadTransactions(listener: OnDatabaseAccessListener<List<Transaction>>) {
        listener.onSelectSuccess(mTransactionBussiness.getTransactions())
    }

    override fun getUserInfo(listener : EventResponseListener<User>) {
        listener.onSuccess(mUserBussiness.getCurrentUser())
    }

    override fun updateProfile(user: User): Boolean {
        return mUserBussiness.updateUser(user)
    }

    override fun loadCoins() {
        mTransactionBussiness.callServices(this)
    }

    override fun onSuccess(obj: String) {
        mView.onSuccess(obj)
    }

    override fun onError(errorMessage: String) {
        mView.showError(errorMessage)
    }
}