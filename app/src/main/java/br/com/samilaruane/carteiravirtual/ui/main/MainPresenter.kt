package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener

/**
 * Created by samila on 07/01/18.
 */
class MainPresenter : MainContract.Presenter {

    lateinit var mView : MainContract.View
    lateinit var mUserBussiness : UserBusiness
    lateinit var mTransactionBussiness : TransactionBusiness

    override fun attachView(view: MainContract.View) {
        mView = view
        mUserBussiness = UserBusiness(mView as Context)
        mTransactionBussiness = TransactionBusiness(mView as Context, mUserBussiness.getCurrentUser())
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