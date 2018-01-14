package br.com.samilaruane.carteiravirtual.ui.main

/**
 * Created by samila on 07/01/18.
 */
class MainPresenter : MainContract.Presenter {

    lateinit var mView : MainContract.View

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}