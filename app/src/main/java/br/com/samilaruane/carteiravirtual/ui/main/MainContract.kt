package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener

/**
 * Created by samila on 07/01/18.
 */
interface MainContract {
    interface View : BaseView {
        fun initViews ()
    }
    interface Presenter : BasePresenter<View> {
        fun loadAccounts(listener : OnDatabaseAccessListener)
        fun loadTransactions ()
    }
}