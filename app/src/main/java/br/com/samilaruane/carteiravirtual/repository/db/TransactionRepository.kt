package br.com.samilaruane.carteiravirtual.repository.db

import android.content.ContentValues
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import br.com.samilaruane.carteiravirtual.domain.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 07/01/18.
 */
@Singleton
class TransactionRepository  : Repository<Transaction>{

    val mWalletDatabaseHelper : WalletDatabaseHelper

    @Inject
    constructor(dbHelper: WalletDatabaseHelper){
        mWalletDatabaseHelper = dbHelper
    }

    override fun create(item: Transaction): Long {
        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues =  ContentValues ()
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.DATE, item.date)
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.SOURCE_ACCOUNT, item.sourceCoin)
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.DESTINATION_ACCOUNT, item.destinationCoin)
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.AMOUNT, item.amount)
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.TYPE, item.transactionType)


        return db.insert(DatabaseConstants.TRANSACTION.TABLE_NAME, null, insertValues)

    }

    override fun delete(item: Transaction) {

    }

    override fun update(item: Transaction) {

    }

    override fun select(statment: Statment): List<Transaction> {
        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = statment.getQuery()

        val list = mutableListOf<Transaction>()
        val cursor = db.rawQuery(querySQL, null)

        while (cursor.moveToNext()){
            val date = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.TRANSACTION.COLUMNS.DATE))
            val type = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TRANSACTION.COLUMNS.TYPE))
            val amount = cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.TRANSACTION.COLUMNS.AMOUNT))
            val sourceCoin = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TRANSACTION.COLUMNS.SOURCE_ACCOUNT))
            val destinationCoin = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TRANSACTION.COLUMNS.DESTINATION_ACCOUNT))

            val item = Transaction(date, type, amount, sourceCoin, destinationCoin)
            list.add(item)
        }
        return list
    }

}