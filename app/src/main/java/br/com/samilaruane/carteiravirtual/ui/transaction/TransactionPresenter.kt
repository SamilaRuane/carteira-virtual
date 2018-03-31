package br.com.samilaruane.carteiravirtual.ui.transaction

import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OperationType
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import javax.inject.Inject

/**
 * Created by samila on 07/01/18.
 */
class TransactionPresenter @Inject constructor(var mView: TransactionContract.View, var mTransactionBusiness: TransactionBusiness, var mUserBusiness: UserBusiness) : TransactionContract.Presenter, EventResponseListener<String> {

    override fun detachView() {
        mView = null!!
    }

    override fun saveTransaction(operationType: String, sourceAccount: String, destinationAccount: String, amount: Double) {
        var type : OperationType = OperationType.BUY

        when (operationType){
            BaseConstants.SELL -> type = OperationType.SALE
            BaseConstants.BUY -> type = OperationType.BUY
            BaseConstants.TRADE -> type = OperationType.TRADE
        }

        mTransactionBusiness.process(type,
                sourceAccount,
                destinationAccount,
                amount, mUserBusiness.getCurrentUser())
    }

    override fun onSuccess(obj: String) {
        mView.onSuccess(obj)
    }

    override fun onError(errorMessage: String) {
        mView.showError(errorMessage)
    }

    override fun loadServiceData() {
        mTransactionBusiness.callServices(this)
    }
}