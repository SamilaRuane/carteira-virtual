package br.com.samilaruane.carteiravirtual.ui.transaction

import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView

/**
 * Created by samila on 07/01/18.
 */
interface TransactionContract {

    interface View : BaseView {
        fun initViews ()
    }

    interface Presenter : BasePresenter<View>{
        fun saveTransaction (operationType : String, sourceAccount : String, destinationAccount : String, amount: Double )
    }
}