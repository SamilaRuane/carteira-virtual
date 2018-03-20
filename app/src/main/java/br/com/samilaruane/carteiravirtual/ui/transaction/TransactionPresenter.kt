package br.com.samilaruane.carteiravirtual.ui.transaction

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.OperationType
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants

/**
 * Created by samila on 07/01/18.
 */
class TransactionPresenter : TransactionContract.Presenter, EventResponseListener<String> {

    lateinit var mView : TransactionContract.View
    lateinit var mTransactionBusiness: TransactionBusiness
    lateinit var mUserBusiness: UserBusiness


    override fun attachView(view: TransactionContract.View) {
        mView = view
        mUserBusiness = UserBusiness(mView as Context)
        mTransactionBusiness = TransactionBusiness(mView as Context, mUserBusiness.getCurrentUser())
    }

    override fun detachView() {
        mView = null!! //TODO checar se é correto fazer esse tipo de atribuição.
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
                amount, this)
    }

    override fun onSuccess(obj: String) {
        mView.onSuccess()
    }

    override fun onError(errorMessage: String) {
        mView.onError(errorMessage)
    }
}