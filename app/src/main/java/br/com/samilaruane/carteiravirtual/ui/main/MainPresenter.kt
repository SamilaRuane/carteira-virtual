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
class MainPresenter : MainContract.Presenter {

     var mView : MainContract.View
     var mUserBussiness : UserBusiness
     var mTransactionBussiness : TransactionBusiness

    @Inject
    constructor(mView: MainContract.View, mUserBussiness: UserBusiness, mTransactionBussiness: TransactionBusiness) {
        this.mView = mView
        this.mUserBussiness = mUserBussiness
        this.mTransactionBussiness = mTransactionBussiness
    }

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
}