package br.com.samilaruane.carteiravirtual.di.modules

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.ui.main.MainContract
import br.com.samilaruane.carteiravirtual.ui.main.MainInteractor
import br.com.samilaruane.carteiravirtual.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 23/03/18.
 */

@Module
class MainModule(private val activity: MainContract.View) {

    @Provides
    fun provideMainPresenter(view: MainContract.View, interactor : MainContract.Interactor): MainContract.Presenter =
            MainPresenter(view, interactor)
    @Provides
    fun provideMainView(): MainContract.View = activity

    @Provides
    fun provideContext(): Context = activity as Context

    @Provides
    fun provideInteractor (userBusiness : UserBoundary, transactionBusiness : TransactionBoundary, accountBusiness: AccountBoundary) : MainContract.Interactor =
            MainInteractor (userBusiness, transactionBusiness, accountBusiness)


}