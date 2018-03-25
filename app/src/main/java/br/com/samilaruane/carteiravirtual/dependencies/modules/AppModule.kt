package br.com.samilaruane.carteiravirtual.dependencies.modules

import android.content.Context
import br.com.samilaruane.carteiravirtual.App
import br.com.samilaruane.carteiravirtual.dependencies.PerActivity
import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.db.*
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

    /*@Provides
    fun provideMainPresenter(view: MainContract.View, mUserBussiness: UserBusiness, mTransactionBussiness: TransactionBusiness): MainContract.Presenter =
            MainPresenter(view, mUserBussiness, mTransactionBussiness)*/

    @Provides
    fun provideUserBussiness(preferences : SharedPreferencesHelper, accountRepository: Repository<Account>, userRepository: UserRepository): UserBusiness =
            UserBusiness(preferences, accountRepository, userRepository)

    @Provides
    fun provideTransactionBussiness(userBussiness: UserBusiness, transactionRepository: Repository<Transaction>): TransactionBusiness =
            TransactionBusiness( userBussiness, transactionRepository )

   /* @Provides
    fun provideMainView(): MainContract.View = MainActivity()
*/
    @Provides
    fun provideAccountRepository(dbHelper: WalletDatabaseHelper): Repository<Account> =
            AccountRepository(dbHelper)

    @Provides
    fun provideRegisterPresenter(view: RegisterContract.View, userBusiness: UserBusiness, preferencesHelper: SharedPreferencesHelper): RegisterContract.Presenter =
            RegisterPresenter(view, userBusiness, preferencesHelper)

    @Provides
    fun provideTransactionPresenter(view: TransactionContract.View, transactionBusiness: TransactionBusiness, mUserBusiness: UserBusiness): TransactionContract.Presenter =
            TransactionPresenter(view, transactionBusiness, mUserBusiness)

    @Provides
    fun provideRegisterView(): RegisterContract.View = RegisterActivity()

    @Provides
    fun provideTransactionView(): TransactionContract.View = TransactionActivity()

    @Provides
    fun provideLoginPresenter(view: LoginContract.View, userBusiness: UserBusiness): LoginContract.Presenter = LoginPresenter(userBusiness, view)

    @Provides
    fun provideLoginView () : LoginContract.View = LoginActivity ()

    @Provides
    fun provideUserRepository (dbHelper : WalletDatabaseHelper) : Repository<User> =
            UserRepository(dbHelper)
    @Provides
    fun provideTransactionRepository (dbHelper: WalletDatabaseHelper) : Repository <Transaction> =
            TransactionRepository(dbHelper)
}