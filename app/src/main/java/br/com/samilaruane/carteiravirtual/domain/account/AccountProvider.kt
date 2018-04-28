package br.com.samilaruane.carteiravirtual.domain.account

import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.data.db.Repository
import br.com.samilaruane.carteiravirtual.data.db.SearchFilter
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import javax.inject.Inject

class AccountProvider @Inject constructor(val repository: Repository<Account>, val preferences:SharedPreferencesHelper): AccountGateway {
    override fun create(account: Account) = repository.create(account)


    override fun edit(account: Account) : Boolean {
        repository.update(account)
        return true
    }

    override fun get(id: Long): List<Account> = repository.select(
            SearchFilter.getByArgument(DatabaseConstants.ACCOUNT.TABLE_NAME,
                    DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, id.toString())
    )

    override fun setBitcoinQuotation(quotation: String) {
        preferences.setBitcoinQuotation(quotation)
    }

    override fun setBritaQuotation(quotation: String) {
        preferences.setBritaQuotation(quotation)
    }

    override fun getBitcoinQuotation(): String =
        preferences.getBitcoinQuotation()


    override fun getBritaQuotation(): String =
            preferences.getBritaQuotation()

}