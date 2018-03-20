package br.com.samilaruane.carteiravirtual.domain

import android.content.Context
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.repository.db.AccountRepository
import br.com.samilaruane.carteiravirtual.repository.db.SearchFilter
import br.com.samilaruane.carteiravirtual.repository.db.UserRepository
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants

/**
 * Created by samila on 02/01/18.
 */
class UserBusiness(ctx: Context) {

    private val userRepository: UserRepository
    private val accountRepository: AccountRepository
    private var brlAccount: Account = Account(0, 0, BRLCoin(), 0.0)
    private var britaAccount: Account = Account(0, 0, BritaCoin(), 0.0)
    private var bitCoinAccount: Account = Account(0, 0, BTCoin(), 0.0)
    private val context: Context

    init {
        userRepository = UserRepository.getInstance(ctx)
        accountRepository = AccountRepository.getInstance(ctx)
        context = ctx
    }

    fun createUser(name: String, email: String, phone: String, password: String, passwordConfirmation: String, listener: EventResponseListener<String>) {

        if (password.equals(passwordConfirmation)) {
            val user = User(0, name, phone, email, password)
            val userId = userRepository.create(user)

            if (user.isValid()) {
                if (userId != null) {
                    accountRepository.create(Account(0, userId, BRLCoin(), 100000.00))
                    accountRepository.create(Account(0, userId, BritaCoin(), 0.0))
                    accountRepository.create(Account(0, userId, BTCoin(), 0.0))
                }

                listener.onSuccess("Usuário criado com sucesso!")
            }
        }else {
            listener.onError(context.getString(R.string.create_user_error_alert))
        }
    }

    fun initAccounts(user: User) {
        val accounts = accountRepository.select(SearchFilter.getByArgument(DatabaseConstants.ACCOUNT.TABLE_NAME, DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, user.id.toString()))
        for (acc: Account in accounts) {
            when (acc.getCoin().getCoinInitials()) {
                BaseConstants.BRITA_ACCOUNT -> britaAccount = acc
                BaseConstants.BITCOIN_ACCOUNT -> bitCoinAccount = acc
                BaseConstants.BRL_ACCOUNT -> brlAccount = acc
            }

        }
    }

    fun getAccountByCoin(coinName: String, user: User): Account? {
        initAccounts(user)
        when (coinName) {
            BaseConstants.BRL_ACCOUNT -> return brlAccount
            BaseConstants.BRITA_ACCOUNT -> return britaAccount
            BaseConstants.BITCOIN_ACCOUNT -> return bitCoinAccount
        }

        return null
    }

    fun getCurrentUser(): User {
        val id = SharedPreferencesHelper(context).getUserId()
        return userRepository.select(SearchFilter.getById(DatabaseConstants.USER.TABLE_NAME, DatabaseConstants.USER.COLUMNS.ID, id.toString())).single()
    }

    fun login(phone: String, password: String): Boolean {
        var user: User?
        if (!phone.isNullOrEmpty() && !password.isNullOrEmpty()) {
            user = userRepository.select(SearchFilter.getByArgument(DatabaseConstants.USER.TABLE_NAME, DatabaseConstants.USER.COLUMNS.PHONE, phone)).singleOrNull()
            if (user != null) {
                if (CredencialsManager.authenticateUser(user, password)) {
                    SharedPreferencesHelper(context).saveUserId(user)
                    return true
                }
                return false
            }
        }
        return false
    }

    fun getUserAccounts(): List <Account> {
        initAccounts(getCurrentUser())
        val accounts = ArrayList<Account>()
        accounts.add(britaAccount)
        accounts.add(bitCoinAccount)
        accounts.add(brlAccount)
        return accounts
    }

    fun updateAccount (account : Account?){
        account?.let {
            accountRepository.update(it)
        }
    }

}