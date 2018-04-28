package br.com.samilaruane.carteiravirtual.ui.transaction

import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import javax.inject.Inject

class TransactionInteractor @Inject constructor(val transactionBusiness : TransactionBoundary, val accountBusiness : AccountBoundary, val userBusiness : UserBoundary) : TransactionContract.Interactor {

       override fun callServices(listener: EventResponseListener<String>) {
        accountBusiness.callServices(listener)
    }

    override fun process(operationType: String, sourceCoin: String, destinationCoin: String, amount: Double): Boolean {
        val accounts : List<Account> = accountBusiness.getFrom(userBusiness.getCurrent().id)
        val source = accountBusiness.getAccount(sourceCoin, accounts)
        val destination = accountBusiness.getAccount(destinationCoin, accounts)

        source?.setCoin(accountBusiness.getQuotation(sourceCoin))
        destination?.setCoin(accountBusiness.getQuotation(destinationCoin))

        val result = transactionBusiness.process(operationType,
                source,
                destination, amount)

        if(source != null && destination != null && result) {
            accountBusiness.edit(source)
            accountBusiness.edit(destination)
            transactionBusiness.save(source, destination, amount,
                    operationType, userBusiness.getCurrent().id)
        }

        return result
    }
}