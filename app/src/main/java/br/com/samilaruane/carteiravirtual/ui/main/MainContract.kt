package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener

/**
 * Created by samila on 07/01/18.
 */
interface MainContract {
    interface View : BaseView {
        fun initViews ()
        fun onSuccess (msg : String)
    }
    interface Presenter : BasePresenter<View> {
        fun loadAccounts(listener : OnDatabaseAccessListener <List<Account>>)
        fun loadTransactions (listener : OnDatabaseAccessListener<List<Transaction>>)
        fun getUserInfo (listener : EventResponseListener<User>)
        fun updateProfile (user : User) : Boolean
        fun loadCoins ()
    }
}