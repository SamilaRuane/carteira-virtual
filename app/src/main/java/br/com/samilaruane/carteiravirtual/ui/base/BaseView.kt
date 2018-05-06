package br.com.samilaruane.carteiravirtual.ui.base

/**
 * Created by samila on 27/12/17.
 */
interface BaseView {
    fun onError(error : String)
    fun initDependencies ()
}