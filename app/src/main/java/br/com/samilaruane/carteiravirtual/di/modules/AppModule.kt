package br.com.samilaruane.carteiravirtual.di.modules

import android.content.Context
import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.data.db.*
import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.account.AccountBusiness
import br.com.samilaruane.carteiravirtual.domain.account.AccountGateway
import br.com.samilaruane.carteiravirtual.domain.account.AccountProvider
import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.domain.auth.AuthBusiness
import br.com.samilaruane.carteiravirtual.domain.auth.AuthGateway
import br.com.samilaruane.carteiravirtual.domain.auth.AuthProvider
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBoundary
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionGateway
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionProvider
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserGateway
import br.com.samilaruane.carteiravirtual.domain.user.UserProvider
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 21/03/18.
 */
@Module
class AppModule(app: App) {

    private val mApp: App = app

    @Provides
    fun provideContext(): Context = mApp

    @Provides
    fun provideDbHelber(): WalletDatabaseHelper =
            WalletDatabaseHelper(mApp)

    @Provides
    fun providePreferencesHelper(): SharedPreferencesHelper =
            SharedPreferencesHelper(mApp)

     @Provides
    fun provideAccountRepository(dbHelper: WalletDatabaseHelper): Repository<Account> =
            AccountRepository(dbHelper)

    @Provides
    fun provideUserRepository (dbHelper : WalletDatabaseHelper) : Repository<User> =
            UserRepository(dbHelper)
    @Provides
    fun provideTransactionRepository (dbHelper: WalletDatabaseHelper) : Repository <Transaction> =
            TransactionRepository(dbHelper)

    @Provides
    fun provideAuthBusiness (authGateway: AuthGateway) :
            AuthBoundary = AuthBusiness (authGateway)

    @Provides
    fun provideAccountBusiness (accountGateway: AccountGateway) :
            AccountBoundary = AccountBusiness (accountGateway)

    @Provides
    fun provideUserBusiness (userGateway: UserGateway) :
            UserBoundary = br.com.samilaruane.carteiravirtual.domain.user.UserBusiness(userGateway)

    @Provides
    fun provideAuthGateway (repository : Repository<User>, preferences :
    SharedPreferencesHelper) : AuthGateway = AuthProvider (repository, preferences)

    @Provides
    fun provideUserGateway (repository: Repository<User>, preferences: SharedPreferencesHelper) :
            UserGateway = UserProvider (repository, preferences)

    @Provides
    fun provideAccountGateway (repository: Repository<Account>, preferences : SharedPreferencesHelper) : AccountGateway =
            AccountProvider (repository, preferences)

    @Provides
    fun provideTransactionGateway(repository: Repository<Transaction>): TransactionGateway =
            TransactionProvider(repository)

    @Provides
    fun provideTransactionBusiness(gateway: TransactionGateway) : TransactionBoundary =
            TransactionBusiness(gateway)
}