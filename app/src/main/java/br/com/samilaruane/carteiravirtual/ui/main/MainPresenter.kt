package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.DataCallback
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import javax.inject.Inject

/**
 * Created by samila on 07/01/18.
 */
class MainPresenter @Inject constructor(var mView: MainContract.View, val mInteracor : MainContract.Interactor) : MainContract.Presenter, EventResponseListener<String> {

    override fun detachView() {
        mView = null!!
    }

    override fun loadAccounts(listener: DataCallback<List<Account>>) {
        listener.onSuccess(mInteracor.getAccounts())
    }

    override fun loadTransactions(listener: DataCallback<List<Transaction>>) {
        listener.onSuccess(mInteracor.getTransactions())
    }

    override fun getUserInfo(listener : EventResponseListener<User>) {
        listener.onSuccess(mInteracor.getUser())
    }

    override fun updateProfile(user: User): Boolean {
        return mInteracor.updateUser(user)
    }

    override fun loadCoins() {
        mInteracor.loadCoins(this)
    }

    override fun onSuccess(obj: String) {
        mView.onSuccess(obj)
    }

    override fun onError(errorMessage: String) {
        mView.onError(errorMessage)
    }
}