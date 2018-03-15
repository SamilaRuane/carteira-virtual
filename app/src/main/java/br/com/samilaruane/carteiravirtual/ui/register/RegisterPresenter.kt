package br.com.samilaruane.carteiravirtual.ui.register

import android.content.Context
import android.telephony.SmsManager
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.utils.NumericTokenGenerator
import br.com.samilaruane.carteiravirtual.utils.OnEventResponse

/**
 * Created by samila on 02/01/18.
 */
class RegisterPresenter : RegisterContract.Presenter, OnEventResponse {

    lateinit var mView : RegisterContract.View
    lateinit var mUserBusiness: UserBusiness
    lateinit var sharedPreference : SharedPreferencesHelper

    override fun attachView(view: RegisterContract.View) {
        mView = view
        mUserBusiness = UserBusiness(view as Context)
        sharedPreference = SharedPreferencesHelper(view as Context)
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(name :  String, email : String, phone : String, password : String, passwordConfirmation : String) {
        mUserBusiness.createUser(name, email, phone, password, passwordConfirmation, this)
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
        val generator = NumericTokenGenerator()
        return  generator.generateToken()
    }

    override fun saveTokenOnPreference(phoneNumber : String, token: String) {
        sharedPreference.keepTokenForConfirmation(phoneNumber, token)
    }

    override fun extractNumbersOfTelephoneMask(maskedPhoneNumber: String) : String {

        var numberNoCaracteres = maskedPhoneNumber.replace("+","")
        numberNoCaracteres = numberNoCaracteres.replace("-", "")
        numberNoCaracteres = numberNoCaracteres.replace("(", "")
        numberNoCaracteres = numberNoCaracteres.replace(")", "")

        return numberNoCaracteres
    }

    override fun getToken(): String {
        return sharedPreference.getToken()
    }

    override fun onSuccess() {

    }

    override fun onError(errorMessage: String) {
        mView.showError(errorMessage)
    }
}