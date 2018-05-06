package br.com.samilaruane.carteiravirtual.di.components

import br.com.samilaruane.carteiravirtual.di.PerActivity
import br.com.samilaruane.carteiravirtual.di.modules.TransactionModule
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionActivity
import dagger.Component

/**
 * Created by samila on 25/03/18.
 */

@PerActivity
@Component(modules = arrayOf(TransactionModule :: class), dependencies = arrayOf(AppComponent :: class))
interface TransactionComponent {
    fun inject (activity : TransactionActivity)
}