package br.com.samilaruane.carteiravirtual.data.db

import android.content.ContentValues
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Coin
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 07/01/18.
 */
@Singleton
class AccountRepository @Inject constructor(val mWalletDatabaseHelper: WalletDatabaseHelper) : Repository<Account>{

    override fun create(item: Account): Long {
        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, item.getUserId())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE, item.getAccountBalance())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS, item.getCoin().name)

        return db.insert(DatabaseConstants.ACCOUNT.TABLE_NAME, null, insertValues)
    }

    override fun delete(item: Account) {

    }

    override fun update(item: Account) {
        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, item.getUserId())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE, item.getAccountBalance())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS, item.getCoin().name)

        db.update(DatabaseConstants.ACCOUNT.TABLE_NAME,  insertValues, "id=${item.getId()}", null)
    }

    override fun select(statment: Statment): List<Account> {

        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = statment.getQuery()

        val list = mutableListOf<Account>()
        val cursor = db.rawQuery(querySQL, null)

        while (cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.ID))
            val userId = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID))
            val balance = cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE))
            val coin = cursor.getString(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS))

            val coinReference = Coin (coin, 1.0, 1.0)

            val item = Account(id, userId, coinReference, balance)

            list.add(item)
        }
        return list
    }
}