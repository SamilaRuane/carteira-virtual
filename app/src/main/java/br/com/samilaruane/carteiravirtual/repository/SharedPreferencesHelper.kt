package br.com.samilaruane.carteiravirtual.repository

import android.content.Context
import android.content.SharedPreferences
import br.com.samilaruane.carteiravirtual.domain.entities.User
import javax.inject.Inject

/**
 * Created by samila on 02/01/18.
 */
class SharedPreferencesHelper @Inject constructor(context: Context) {
    val SHARED_PREFERENCE_NAME = "tokens"
    val SHARED_PREFERENCE_USER_TOKEN = "userToken"
    val SHARED_PREFERENCE_USER_ID = "userId"
    val SHARED_PREFERENCES_CHECK_IS_AUTH = "isAuth"
    val SHARED_PREFERENCES_BRITA_QUOTATION = "britaQuotation"
    val SHARED_PREFERENCES_BITCOIN_QUOTATION = "bitcoinQuotation"


    private val sharedPreference : SharedPreferences

    fun keepTokenForConfirmation (userPhoneNumber : String, token : String ){
        sharedPreference.edit().putString(SHARED_PREFERENCE_USER_TOKEN, token).commit ()
    }

    fun getToken () : String {
        return sharedPreference.getString(SHARED_PREFERENCE_USER_TOKEN, "")
    }

    fun saveUserId(user : User){
        if(user.id != null) {
            sharedPreference.edit().putLong(SHARED_PREFERENCE_USER_ID, user.id).commit()
        }
    }

    fun getUserId () : Long{
        return sharedPreference.getLong(SHARED_PREFERENCE_USER_ID, 0)
    }

    fun setIsAuth (auth : Boolean){
        sharedPreference.edit().putBoolean(SHARED_PREFERENCES_CHECK_IS_AUTH, auth).commit()
    }


    fun isAuth () : Boolean {
        return sharedPreference.getBoolean(SHARED_PREFERENCES_CHECK_IS_AUTH, false)
    }

    fun setBritaQuotation (body : String){
        sharedPreference.edit().putString(SHARED_PREFERENCES_BRITA_QUOTATION, body).commit()
    }

    fun getBritaQuotation () : String {
        return sharedPreference.getString(SHARED_PREFERENCES_BRITA_QUOTATION, "")
    }

    fun setBitcoinQuotation (body : String){
        sharedPreference.edit().putString(SHARED_PREFERENCES_BITCOIN_QUOTATION, body).commit()
    }

    fun getBitcoinQuotation () : String {
        return sharedPreference.getString(SHARED_PREFERENCES_BITCOIN_QUOTATION, "")
    }

    init {
        sharedPreference = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}