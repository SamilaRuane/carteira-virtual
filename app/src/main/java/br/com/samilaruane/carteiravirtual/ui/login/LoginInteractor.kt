package br.com.samilaruane.carteiravirtual.ui.login

import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary

class LoginInteractor (private val authBusiness: AuthBoundary) : LoginContract.Interactor {

    override fun authenticate(phone: String, password: String) : Boolean{
        return authBusiness.authenticate(phone, password)
    }

    override fun exit() {
        authBusiness.exit()
    }
}