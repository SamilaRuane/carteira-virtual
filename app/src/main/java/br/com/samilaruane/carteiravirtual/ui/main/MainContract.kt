package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView
import br.com.samilaruane.carteiravirtual.utils.DataCallback
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener

/**
 * Created by samila on 07/01/18.
 */
interface MainContract {
    interface View : BaseView {
        fun initViews ()
        fun onSuccess (msg : String)
    }
    interface Presenter : BasePresenter<View> {
        fun loadAccounts(listener : DataCallback<List<Account>>)
        fun loadTransactions (listener : DataCallback<List<Transaction>>)
        fun getUserInfo (listener : EventResponseListener<User>)
        fun updateProfile (user : User) : Boolean
        fun loadCoins ()
    }

    interface Interactor {
        fun getAccounts () : List <Account>
        fun getTransactions () : List <Transaction>
        fun getUser () : User
        fun updateUser (user : User) : Boolean
        fun loadCoins (listener : EventResponseListener<String>)
    }
}