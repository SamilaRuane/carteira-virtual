package br.com.samilaruane.carteiravirtual.ui.transaction

import android.content.Context
import br.com.samilaruane.carteiravirtual.domain.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.UserBusiness

/**
 * Created by samila on 07/01/18.
 */
class TransactionPresenter : TransactionContract.Presenter {

    lateinit var mView : TransactionContract.View
    lateinit var mTransactionBusiness: TransactionBusiness
    lateinit var mUserBusiness: UserBusiness


    override fun attachView(view: TransactionContract.View) {
        mView = view
        mTransactionBusiness = TransactionBusiness(mView as Context)
        mUserBusiness = UserBusiness(mView as Context)
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTransaction(operationType: String, sourceAccount: String, destinationAccount: String, amount: Double) {
        mTransactionBusiness.manageTransaction(operationType,
                sourceAccount,
                destinationAccount,
                amount,
                mUserBusiness.getCurrentUser())
    }
}