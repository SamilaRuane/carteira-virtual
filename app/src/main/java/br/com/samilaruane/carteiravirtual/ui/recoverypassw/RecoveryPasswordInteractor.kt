package br.com.samilaruane.carteiravirtual.ui.recoverypassw

import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import javax.inject.Inject

class RecoveryPasswordInteractor @Inject constructor(val authBusiness: AuthBoundary, val userBusiness : UserBoundary) : RecoveryPasswordContract.Interactor {

    private var user : User? = null

    override fun sendToken(phoneNumber: String) : Boolean{
        var result = false

        if(userBusiness.userExists(phoneNumber)) {
            authBusiness.sendToken(phoneNumber)
            user = userBusiness.getUser(phoneNumber)
            result = true
        }
        return result
    }

    override fun verifyTokenMatcher(token: String): Boolean =
        authBusiness.checkIfMatches(token)


    override fun changePassword(newPass: String): Boolean {
        var result = false
        if(user != null){
            user!!.password = newPass
            userBusiness.changePassword(user!!)
            result = true
        }

        return result
    }
}