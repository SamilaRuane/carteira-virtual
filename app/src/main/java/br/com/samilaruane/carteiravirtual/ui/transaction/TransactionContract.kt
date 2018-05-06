package br.com.samilaruane.carteiravirtual.ui.transaction

import br.com.samilaruane.carteiravirtual.ui.base.BasePresenter
import br.com.samilaruane.carteiravirtual.ui.base.BaseView
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener

/**
 * Created by samila on 07/01/18.
 */
interface TransactionContract {

    interface View : BaseView {
        fun initViews ()
        fun onSuccess (msg : String)
    }

    interface Presenter : BasePresenter<View>{
        fun saveTransaction (operationType : String, sourceAccount : String, destinationAccount : String, amount: Double )
        fun loadServiceData ()
    }

    interface Interactor {
        fun callServices (listener : EventResponseListener<String>)
        fun process (operationType: String, sourceCoin: String,
                     destinationCoin: String, amount: Double) : Boolean
    }
}