package br.com.samilaruane.carteiravirtual.ui.login

import br.com.samilaruane.carteiravirtual.base.BasePresenter
import br.com.samilaruane.carteiravirtual.base.BaseView
import br.com.samilaruane.carteiravirtual.domain.User

/**
 * Created by samila on 27/12/17.
 */
interface LoginContract {
    interface View : BaseView{
        fun showProgress ()
        fun hideProgress ()
        fun navigateTo (cls : Class <*>)
    }

    interface Presenter : BasePresenter<View> {
        fun login(phone : String, password : String)
    }
}