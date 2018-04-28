package br.com.samilaruane.carteiravirtual.ui.transaction

import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import javax.inject.Inject

/**
 * Created by samila on 07/01/18.
 */
class TransactionPresenter @Inject constructor(var mView: TransactionContract.View, val mInteractor: TransactionContract.Interactor) : TransactionContract.Presenter, EventResponseListener<String> {

    override fun detachView() {
        mView = null!!
    }

    override fun saveTransaction(operationType: String, sourceAccount: String, destinationAccount: String, amount: Double) {
       if( mInteractor.process(operationType,
                sourceAccount,
                destinationAccount,
                amount) ) {
           mView.onSuccess(BaseConstants.MESSAGES.SUCCESS_ON_TASK)
       }else {
           mView.onError(BaseConstants.MESSAGES.INSUFFICIENT_BALANCE)
       }
    }

    override fun onSuccess(obj: String) {
        mView.onSuccess(obj)
    }

    override fun onError(errorMessage: String) {
        mView.onError(errorMessage)
    }

    override fun loadServiceData() {
        mInteractor.callServices(this)
    }
}