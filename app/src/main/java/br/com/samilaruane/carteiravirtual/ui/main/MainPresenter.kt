package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener

/**
 * Created by samila on 07/01/18.
 */
class MainPresenter : MainContract.Presenter {

    lateinit var mView : MainContract.View
    lateinit var mUserBussiness : UserBusiness

    override fun attachView(view: MainContract.View) {
        mView = view
        mUserBussiness = UserBusiness(mView as Context)
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAccounts(listener : OnDatabaseAccessListener) {
       listener.onSelectSuccess(mUserBussiness.getUserAccounts() as List<Object>)
    }

    override fun loadTransactions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}