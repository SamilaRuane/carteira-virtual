package br.com.samilaruane.carteiravirtual.ui.base

/**
 * Created by samila on 27/12/17.
 */
interface BasePresenter <in T : BaseView>  {
    fun attachView(view : T)
    fun detachView()
}