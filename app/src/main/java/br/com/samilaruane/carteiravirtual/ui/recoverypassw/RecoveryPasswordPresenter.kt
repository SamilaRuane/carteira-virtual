package br.com.samilaruane.carteiravirtual.ui.recoverypassw

import javax.inject.Inject

class RecoveryPasswordPresenter @Inject constructor(val mView : RecoveryPasswordContract.View,
                                 val mInteractor: RecoveryPasswordContract.Interactor) : RecoveryPasswordContract.Presenter{

    override fun sendRecoveryCode(phoneNumber: String) : Boolean = mInteractor.sendToken(phoneNumber)

    override fun checkRecoveryCode(code: String): Boolean = mInteractor.verifyTokenMatcher(code)

    override fun changePassword(newPass: String): Boolean = mInteractor.changePassword(newPass)

    override fun detachView() {
        mView = null!!
    }
}