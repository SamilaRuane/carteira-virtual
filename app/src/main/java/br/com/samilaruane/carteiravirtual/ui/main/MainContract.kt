package br.com.samilaruane.carteiravirtual.ui.main

import br.com.samilaruane.carteiravirtual.base.BasePresenter
import br.com.samilaruane.carteiravirtual.base.BaseView

/**
 * Created by samila on 07/01/18.
 */
interface MainContract {
    interface View : BaseView {
        fun initViews ()
    }
    interface Presenter : BasePresenter<View> {

    }
}