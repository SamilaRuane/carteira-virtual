package br.com.samilaruane.carteiravirtual.domain.transaction

import br.com.samilaruane.carteiravirtual.data.db.Repository
import br.com.samilaruane.carteiravirtual.data.db.SearchFilter
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import javax.inject.Inject

class TransactionProvider
@Inject constructor(val repository: Repository<Transaction>) : TransactionGateway {

    override fun create(transaction: Transaction): Boolean = repository.create(transaction) > 0

    override fun edit(transaction: Transaction) = repository.update(transaction)

    override fun get(id: Long): List<Transaction> = repository.select(SearchFilter.getByArgument(
            DatabaseConstants.TRANSACTION.TABLE_NAME,
            DatabaseConstants.TRANSACTION.COLUMNS.USER_ID,
            id.toString()))

}