package br.com.samilaruane.carteiravirtual.di.modules

import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionContract
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionInteractor
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class TransactionModule(private val activity: TransactionContract.View) {

    @Provides
    fun provideView(): TransactionContract.View = activity

    @Provides
    fun providePresenter(view: TransactionContract.View, interactor: TransactionContract.Interactor): TransactionContract.Presenter =
            TransactionPresenter(view, interactor)

    @Provides
    fun provideInteractor(transactionBusiness: TransactionBoundary, accountBusiness: AccountBoundary, userBusiness: UserBoundary): TransactionContract.Interactor =
            TransactionInteractor(transactionBusiness, accountBusiness, userBusiness)

}