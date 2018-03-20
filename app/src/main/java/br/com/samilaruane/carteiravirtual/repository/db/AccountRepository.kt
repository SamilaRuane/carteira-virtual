package br.com.samilaruane.carteiravirtual.repository.db

import android.content.ContentValues
import android.content.Context
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import br.com.samilaruane.carteiravirtual.domain.*
import br.com.samilaruane.carteiravirtual.domain.entities.Account

/**
 * Created by samila on 07/01/18.
 */
class AccountRepository private constructor(ctx : Context) : Repository<Account>{
    var mWalletDatabaseHelper = WalletDatabaseHelper (ctx)

    companion object {
        var sInstance : Repository<Account>? = null

        fun getInstance (ctx : Context) : AccountRepository {
            if(sInstance == null){
                sInstance = AccountRepository(ctx)
            }

            return sInstance as AccountRepository
        }
    }

    override fun create(item: Account): Long {
        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, item.getUserId())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE, item.getAccountBalance())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS, item.getCoin().getCoinInitials())

        return db.insert(DatabaseConstants.ACCOUNT.TABLE_NAME, null, insertValues)
    }

    override fun delete(item: Account) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(item: Account) {
        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, item.getUserId())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE, item.getAccountBalance())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS, item.getCoin().getCoinInitials())

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

            var coinReference : Coin = BRLCoin ()

            when (coin){
                BaseConstants.BITCOIN_ACCOUNT -> {coinReference = BTCoin()}
                BaseConstants.BRITA_ACCOUNT-> {coinReference = BritaCoin ()}
            }

            val item = Account(id, userId, coinReference, balance)

            list.add(item)
        }
        return list
    }
}