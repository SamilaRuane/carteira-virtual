package br.com.samilaruane.carteiravirtual.ui.register

import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView

/**
 * Created by samila on 02/01/18.
 */
interface RegisterContract {
    interface View : BaseView {
        fun onSuccess()
    }

    interface Presenter : BasePresenter<View> {
        fun create (name :  String, email : String, phone : String, password : String, passwordConfirmation : String)
        fun sendMessage (phoneNumber : String)
        fun verifyToken (token : String) : Boolean
    }

    interface Interactor {
        fun create (user : User) : Boolean
        fun sendToken (phoneNumber : String)
        fun getToken () : String
    }

}