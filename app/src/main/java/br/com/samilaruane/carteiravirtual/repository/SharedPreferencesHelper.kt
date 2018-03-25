package br.com.samilaruane.carteiravirtual.repository

import android.content.Context
import android.content.SharedPreferences
import br.com.samilaruane.carteiravirtual.domain.entities.User
import javax.inject.Inject

/**
 * Created by samila on 02/01/18.
 */
class SharedPreferencesHelper {
    val SHARED_PREFERENCE_NAME = "tokens"
    val SHARED_PREFERENCE_USER_TOKEN = "userToken"
    val SHARED_PREFERENCE_USER_ID = "userId"
    val SHARED_PREFERENCES_CHECK_IS_AUTH = "isAuth"


    private val context : Context
    private val sharedPreference : SharedPreferences

    @Inject
    constructor(context: Context){
        this.context = context
        sharedPreference = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

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
}