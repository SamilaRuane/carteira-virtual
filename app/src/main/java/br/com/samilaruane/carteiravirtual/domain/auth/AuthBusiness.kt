package br.com.samilaruane.carteiravirtual.domain.auth

import android.telephony.SmsManager
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.extension.generateToken
import java.util.*

class AuthBusiness(private val gateway: AuthGateway) : AuthBoundary {

    override fun authenticate(phone: String, password: String): Boolean {
        val user: User? = gateway.getUserByPhone(phone)
        var result = false

        user?.let {
            if (it.password == password) {
                gateway.saveCurrentUser(it)
                result = true
            }
        }

        return result
    }

    override fun exit() {
        gateway.invalidateSavedUser()
    }

    override fun sendToken(phoneNumber: String) {
        val random = Random().generateToken()
        val msg = "Use este token para recuperar sua senha"
        gateway.keepToken(phoneNumber, random)
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, "$msg - $random", null, null)
    }

    override fun checkIfMatches(token: String): Boolean {
        var result = false
        if (gateway.getToken() == token) result = true
        return result
    }

    override fun getToken(): String = gateway.getToken()

}