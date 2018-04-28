package br.com.samilaruane.carteiravirtual.ui.recoverypassw

import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView

interface RecoveryPasswordContract {
    interface View : BaseView {
        fun onSuccess ()
    }

    interface Presenter : BasePresenter<View>{
        fun sendRecoveryCode (phoneNumber : String) : Boolean
        fun checkRecoveryCode (code : String) : Boolean
        fun changePassword (newPass : String) : Boolean
    }

    interface Interactor {
        fun sendToken (phoneNumber : String) : Boolean
        fun verifyTokenMatcher (token : String) : Boolean
        fun changePassword (newPass : String) : Boolean
    }
}