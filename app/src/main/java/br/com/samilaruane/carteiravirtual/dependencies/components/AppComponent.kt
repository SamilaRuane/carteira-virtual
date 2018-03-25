package br.com.samilaruane.carteiravirtual.dependencies.components

import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.dependencies.modules.AppModule
import br.com.samilaruane.carteiravirtual.dependencies.modules.MainModule
import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.db.Repository
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.ui.register.RegisterActivity
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by samila on 21/03/18.
 */

@Singleton
@Component (modules = arrayOf(AppModule :: class))
interface AppComponent {
    fun inject (app : App)
    fun inject (registerActivity: RegisterActivity)
    fun inject (transactionActivity : TransactionActivity)
    fun inject (loginActivity : LoginActivity)

    fun getAccountRepository () : Repository<Account>
    fun getUserRepository () : Repository<User>
    fun getTransactionRepository () : Repository<Transaction>
    fun getPreferences () : SharedPreferencesHelper

}