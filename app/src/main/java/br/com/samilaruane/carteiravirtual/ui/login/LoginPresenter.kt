package br.com.samilaruane.carteiravirtual.ui.login

import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import javax.inject.Inject

/**
 * Created by samila on 27/12/17.
 */
class LoginPresenter @Inject constructor(val mInteractor: LoginContract.Interactor,
                                         val mView: LoginContract.View) : LoginContract.Presenter {

    override fun detachView() {
        mView = null!!
    }

    override fun login(phone: String, password: String) {
        when (mInteractor.authenticate(phone, password)) {
            true -> mView.navigateTo(MainActivity::class.java)
            false -> mView.onError(BaseConstants.MESSAGES.INVALID_PASSWORD)
        }
    }

}