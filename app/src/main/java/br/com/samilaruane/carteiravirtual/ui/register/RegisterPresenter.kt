package br.com.samilaruane.carteiravirtual.ui.register

import android.telephony.SmsManager
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.extension.generateToken
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import java.util.*
import javax.inject.Inject

/**
 * Created by samila on 02/01/18.
 */
class RegisterPresenter @Inject constructor(var mView: RegisterContract.View, var mUserBusiness: UserBusiness, var sharedPreference: SharedPreferencesHelper) : RegisterContract.Presenter, EventResponseListener<String> {

    override fun detachView() {
        mView = null!!
    }

    override fun create(name :  String, email : String, phone : String, password : String, passwordConfirmation : String) {
       if(name.isEmpty() ||
                email.isEmpty() ||
                phone.isEmpty() ||
                password.isEmpty() ||
                password.isEmpty()) mView.showError(BaseConstants.MESSAGES.EMPTY_FIELDS)
        else {
            mUserBusiness.createUser(
                    name, email,
                    phone, password,
                    passwordConfirmation, this)
        }
    }

    override fun sendMessage(phoneNumber: String, msg: String) {
        try{
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, msg, null, null)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun generateToken(): String {
        return Random().generateToken ()
    }

    override fun saveTokenOnPreference(phoneNumber : String, token: String) {
        sharedPreference.keepTokenForConfirmation(phoneNumber, token)
    }

    override fun getToken(): String {
        return sharedPreference.getToken()
    }

    override fun onSuccess(obj: String) {
        mView.onSuccess()
    }

    override fun onError(errorMessage: String) {
        mView.showError(errorMessage)
    }
}