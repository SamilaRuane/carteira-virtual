package br.com.samilaruane.carteiravirtual.dependencies.modules

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.ui.main.MainContract
import br.com.samilaruane.carteiravirtual.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 23/03/18.
 */

@Module
class MainModule {

    private val activity: MainContract.View

   /* @Inject
    lateinit var accountRepository : Repository<Account>

    @Inject
    lateinit var userRepository : Repository <User>

    @Inject
    lateinit var transactionRepository : Repository<Transaction>*/

    constructor(activity: MainContract.View) {
        this.activity = activity
    }

  /*  @Inject
    lateinit var mWalletDatabaseHelper : WalletDatabaseHelper

    @Inject
    lateinit var userBussiness : UserBusiness

    @Inject
    lateinit var transactionBusiness : TransactionBusiness

    @Inject
    lateinit var accountRepository : Repository<Account>

    @Inject
    lateinit var userRepository : Repository <User>

    @Inject
    lateinit var transactionRepository : Repository<Transaction>*/


    @Provides
    fun provideMainPresenter(view: MainContract.View, mUserBussiness: UserBusiness, mTransactionBussiness: TransactionBusiness): MainContract.Presenter =
            MainPresenter(view, mUserBussiness, mTransactionBussiness)

    @Provides
    fun provideMainView(): MainContract.View = activity

    @Provides
    fun provideContext(): Context = activity as Context



}