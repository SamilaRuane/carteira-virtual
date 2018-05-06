package br.com.samilaruane.carteiravirtual.ui.register

import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import javax.inject.Inject

/**
 * Created by samila on 02/01/18.
 */
class RegisterPresenter @Inject constructor(var mView: RegisterContract.View, var mInteractor: RegisterContract.Interactor) : RegisterContract.Presenter, EventResponseListener<String> {

    override fun detachView() {
        mView = null!!
    }

    override fun create(name: String, email: String, phone: String, password: String, passwordConfirmation: String) {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                || password.isEmpty() || password.isEmpty())
            mView.onError(BaseConstants.MESSAGES.EMPTY_FIELDS)
        else {
            if (password == passwordConfirmation) {
                val user = User(0, name, phone, email, password)
                if (mInteractor.create(user)) mView.onSuccess()
                else mView.onError(BaseConstants.MESSAGES.USER_ALREADY_EXISTS)
            } else
                mView.onError(BaseConstants.MESSAGES.PASSWORD_NOT_MATCH)
        }
    }

    override fun sendMessage(phoneNumber: String) =
            mInteractor.sendToken(phoneNumber)


    override fun verifyToken(token: String): Boolean =
            token == mInteractor.getToken()


    override fun onSuccess(obj: String) {
        mView.onSuccess()
    }

    override fun onError(errorMessage: String) {
        mView.onError(errorMessage)
    }
}