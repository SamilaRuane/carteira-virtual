package br.com.samilaruane.carteiravirtual.repository

import android.content.Context
import android.util.Log

/**
 * Created by samila on 02/01/18.
 */
class SharedPreferencesHelper(ctx : Context) {
    val SHARED_PREFERENCE_NAME = "tokens"
    val SHARED_PREFERENCE_USER_TOKEN = "userToken"

    val sharedPreference = ctx.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun keepTokenForConfirmation (userPhoneNumber : String, token : String ){
        sharedPreference.edit().putString(SHARED_PREFERENCE_USER_TOKEN, token).commit ()
    }

    fun getToken () : String {
        return sharedPreference.getString(SHARED_PREFERENCE_USER_TOKEN, "")
    }
}