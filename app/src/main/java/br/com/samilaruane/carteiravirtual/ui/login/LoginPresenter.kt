package br.com.samilaruane.carteiravirtual.ui.login

import android.telephony.SmsManager
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.extension.generateToken
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import java.util.*
import javax.inject.Inject

/**
 * Created by samila on 27/12/17.
 */
class LoginPresenter @Inject constructor(val userBusiness: UserBusiness, val mView: LoginContract.View, val preferences: SharedPreferencesHelper) : LoginContract.Presenter {

    override fun detachView() {
        mView = null!!
    }

    override fun login(phone: String, password: String) {
        if(userBusiness.login(phone, password)){
            preferences.setIsAuth(true)
            mView.navigateTo(MainActivity :: class.java)
        }else {
            mView.showError(BaseConstants.MESSAGES.INVALID_PASSWORD)
        }
    }

    override fun sendRecoveryCode(phoneNumber : String, msg : String) : Boolean {
        try {
                if(userBusiness.checkIfExists(phoneNumber)) {
                    val random = Random().generateToken()
                    preferences.keepTokenForConfirmation(phoneNumber,random)
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(phoneNumber, null, "$msg - $random", null, null)
                    return true
                }else {
                    mView.showError(BaseConstants.MESSAGES.USER_NOT_FOUND)
                    return false
                }
            }catch (e : Exception){
            e.printStackTrace()
        }
        return false
    }

    override fun ckeckRecoveryCode(code: String): Boolean =
            code == preferences.getToken()

    override fun changePassword(newPass: String) : Boolean =
        userBusiness.changeUserPassword(newPass)

}