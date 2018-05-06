package br.com.samilaruane.carteiravirtual.di.components

import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.data.db.Repository
import br.com.samilaruane.carteiravirtual.di.modules.AppModule
import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.domain.auth.AuthGateway
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBoundary
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionGateway
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserGateway
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
    fun getUserGateway () : UserGateway
    fun getAuthGateway () : AuthGateway
    fun getTransactionGateway () : TransactionGateway
    fun getUserBoundary () : UserBoundary
    fun getAuthBoundary () : AuthBoundary
    fun getAccountBoundary () : AccountBoundary
    fun getTransactionBoundary () : TransactionBoundary

}