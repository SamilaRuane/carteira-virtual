package br.com.samilaruane.carteiravirtual.ui.login

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity

/**
 * Created by samila on 27/12/17.
 */
class LoginPresenter : LoginContract.Presenter {

    lateinit var userBusiness: UserBusiness
    lateinit var mView : LoginContract.View



    override fun attachView(view: LoginContract.View) {
        this.mView = view
        userBusiness = UserBusiness(view as Context)
    }

    override fun detachView() {
    }

    override fun login(phone: String, password: String) {
        if(userBusiness.login(phone, password)){
            mView.navigateTo(MainActivity :: class.java)
        }else {
            mView.showError("Usuário ou senha inválida")
        }
    }

}