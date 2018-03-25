package br.com.samilaruane.carteiravirtual.ui.login

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import javax.inject.Inject

/**
 * Created by samila on 27/12/17.
 */
class LoginPresenter : LoginContract.Presenter {

    var userBusiness: UserBusiness
     var mView : LoginContract.View

    @Inject
    constructor(userBusiness: UserBusiness, mView: LoginContract.View) {
        this.userBusiness = userBusiness
        this.mView = mView
    }

    override fun detachView() {
    }

    override fun login(phone: String, password: String) {
        if(userBusiness.login(phone, password)){
            SharedPreferencesHelper(mView as Context).setIsAuth(true)
            mView.navigateTo(MainActivity :: class.java)
        }else {
            mView.showError("Usuário ou senha inválida")
        }
    }

}