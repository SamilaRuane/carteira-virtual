package br.com.samilaruane.carteiravirtual.ui.register

import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import javax.inject.Inject

class RegisterInteractor
@Inject constructor(val userBusiness: UserBoundary,
                    val authBusiness: AuthBoundary,
                    val accountBusiness : AccountBoundary) : RegisterContract.Interactor {

    override fun create(user: User): Boolean {
        var result = false

        if(!userBusiness.userExists(user.phone)) {
            val id = userBusiness.create(user)
            if(id > 0) {
                accountBusiness.createAccountsTo(id)
                result = true
            }
        }

        return result
    }

    override fun sendToken(phoneNumber: String) = authBusiness.sendToken(phoneNumber)

    override fun getToken(): String = authBusiness.getToken()
}