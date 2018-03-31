package br.com.samilaruane.carteiravirtual.dependencies.components

import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.dependencies.modules.AppModule
import br.com.samilaruane.carteiravirtual.domain.BTCoin
import br.com.samilaruane.carteiravirtual.domain.BritaCoin
import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.db.Repository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by samila on 21/03/18.
 */

@Singleton
@Component (modules = arrayOf(AppModule :: class))
interface AppComponent {
    fun inject (app : App)

    fun getAccountRepository () : Repository<Account>
    fun getUserRepository () : Repository<User>
    fun getTransactionRepository () : Repository<Transaction>
    fun getPreferences () : SharedPreferencesHelper
    fun getBritaCoin () : BritaCoin
    fun getBitcoin () : BTCoin

}