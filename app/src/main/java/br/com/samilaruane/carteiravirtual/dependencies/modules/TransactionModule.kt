package br.com.samilaruane.carteiravirtual.dependencies.modules

import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionContract
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class TransactionModule(private val activity: TransactionContract.View) {

    @Provides
    fun provideTransactionView () : TransactionContract.View = activity

    @Provides
    fun provideTransactionPresenter (view : TransactionContract.View, transactionBusiness : TransactionBusiness, userBusiness : UserBusiness ) : TransactionContract.Presenter =
            TransactionPresenter(view, transactionBusiness, userBusiness)
}