package br.com.samilaruane.carteiravirtual.dependencies.modules

import android.content.Context
import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.dependencies.PerActivity
import br.com.samilaruane.carteiravirtual.domain.*
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.db.*
import br.com.samilaruane.carteiravirtual.repository.remote.BitcoinService
import br.com.samilaruane.carteiravirtual.repository.remote.BritaService
import br.com.samilaruane.carteiravirtual.repository.remote.Service
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import br.com.samilaruane.carteiravirtual.ui.login.LoginContract
import br.com.samilaruane.carteiravirtual.ui.login.LoginPresenter
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.ui.main.MainContract
import br.com.samilaruane.carteiravirtual.ui.main.MainPresenter
import br.com.samilaruane.carteiravirtual.ui.register.RegisterActivity
import br.com.samilaruane.carteiravirtual.ui.register.RegisterContract
import br.com.samilaruane.carteiravirtual.ui.register.RegisterPresenter
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionActivity
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionContract
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 21/03/18.
 */
@Module
class AppModule {

    val mApp: App

    constructor(app: App) {
        mApp = app
    }

    @Provides
    fun provideContext(): Context = mApp

    @Provides
    fun provideDbHelber(): WalletDatabaseHelper =
            WalletDatabaseHelper(mApp)

    @Provides
    fun providePreferencesHelper(): SharedPreferencesHelper =
            SharedPreferencesHelper(mApp)

    @Provides
    fun provideUserBussiness(preferences : SharedPreferencesHelper, accountRepository: Repository<Account>, userRepository: UserRepository, britacoin:BritaCoin, bitcoin : BTCoin): UserBusiness =
            UserBusiness(preferences, accountRepository, userRepository, britacoin, bitcoin)

    @Provides
    fun provideTransactionBussiness(userBussiness: UserBusiness, transactionRepository: Repository<Transaction>): TransactionBusiness =
            TransactionBusiness( userBussiness, transactionRepository )
 @Provides
    fun provideAccountRepository(dbHelper: WalletDatabaseHelper, britacoin:BritaCoin, bitcoin : BTCoin): Repository<Account> =
            AccountRepository(dbHelper, britacoin, bitcoin)

    @Provides
    fun provideUserRepository (dbHelper : WalletDatabaseHelper) : Repository<User> =
            UserRepository(dbHelper)
    @Provides
    fun provideTransactionRepository (dbHelper: WalletDatabaseHelper) : Repository <Transaction> =
            TransactionRepository(dbHelper)
    @Provides
    fun provideBritaService () : Service<BancoCentralResponse> = BritaService ()

    @Provides
    fun provideBitcoinService () : Service<MercadoBitcoinResponse> = BitcoinService()

    @Provides
    fun provideBtcoin(bitcoinService: Service<MercadoBitcoinResponse>) : BTCoin = BTCoin(bitcoinService)

    @Provides
    fun provideBritacoin(britaService: Service<BancoCentralResponse>) : BritaCoin = BritaCoin(britaService)
}