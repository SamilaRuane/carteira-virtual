package br.com.samilaruane.carteiravirtual.dependencies.components

import br.com.samilaruane.carteiravirtual.dependencies.PerActivity
import br.com.samilaruane.carteiravirtual.dependencies.modules.TransactionModule
import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.repository.remote.BitcoinService
import br.com.samilaruane.carteiravirtual.repository.remote.BritaService
import br.com.samilaruane.carteiravirtual.repository.remote.Service
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