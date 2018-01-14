package br.com.samilaruane.carteiravirtual.domain

import android.content.Context
import br.com.samilaruane.carteiravirtual.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.repository.db.AccountRepository
import br.com.samilaruane.carteiravirtual.repository.db.AccountRepositoryImpl
import br.com.samilaruane.carteiravirtual.repository.db.UserRepository
import br.com.samilaruane.carteiravirtual.repository.db.UserRepositoryImpl

/**
 * Created by samila on 02/01/18.
 */
class UserBusiness(ctx : Context) {

    private var userRepository : UserRepository
    private var accountRepository : AccountRepository
    lateinit var brlAccount : Account
    lateinit var britaAccount : Account
    lateinit var bitCoinAccount : Account

    init {
        userRepository = UserRepositoryImpl.getInstance(ctx)
        accountRepository = AccountRepositoryImpl.getInstance(ctx)
    }

    fun createUser (name :  String, email : String, phone : String, password : String, passwordConfirmation : String){

        if (password.equals(passwordConfirmation)) {
            val user = User(0, name, phone, email, password)
            val userId = userRepository.insert(user)

            if (isValid(user)) {
                if (userId != null) {
                    accountRepository.insert(Account(userId, BRLCoin (), 100000.00))
                    accountRepository.insert(Account(userId, BritaCoin (), 0.0))
                    accountRepository.insert(Account(userId, BTCoin (), 0.0))

                }
            }
        }
    }

    private fun isValid (user : User?) : Boolean{
        if(user != null){
            if((!user.name.isEmpty()) &&
                    (!user.email.isEmpty()) &&
                    (!user.password.isEmpty()))
            return true
        }
            return false
    }

    fun initAccounts (user : User){
        val accounts = accountRepository.select(user.id.toString())
        for (acc : Account in accounts){
            when(acc.getCoin().getCoinInitials()){

                BaseConstants.BRITA_ACCOUNT -> britaAccount = acc
                BaseConstants.BITCOIN_ACCOUNT -> bitCoinAccount = acc
                BaseConstants.BRL_ACCOUNT -> brlAccount = acc
            }

        }
    }
    fun getAccountByCoin (coinName : String, user : User) : Account?{
        initAccounts(user)
        when (coinName){
            BaseConstants.BRL_ACCOUNT -> return brlAccount
            BaseConstants.BRITA_ACCOUNT -> return britaAccount
            BaseConstants.BITCOIN_ACCOUNT -> return bitCoinAccount
        }

        return null
    }

    fun getCurrentUser () : User{
        return User (1, "", "", "", "")
    }

    fun login (phone : String, password: String) : Boolean{
        userRepository.select("")
        var user : User?
        if(!phone.equals("") && !password.equals("") && phone != null && password != null) {
            user = userRepository.selectUserByPhone(phone)
            if(user != null){
                if (user.password.equals(password)){
                    return true
                }
            }
        }

        return false
    }


}