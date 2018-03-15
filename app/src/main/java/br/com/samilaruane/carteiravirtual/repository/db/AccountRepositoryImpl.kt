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
class AccountRepositoryImpl private constructor(ctx : Context) : AccountRepository{
    var mWalletDatabaseHelper = WalletDatabaseHelper (ctx)

    companion object {
        var sInstance : AccountRepository? = null

        fun getInstance (ctx : Context) : AccountRepository {
            if(sInstance == null){
                sInstance = AccountRepositoryImpl (ctx)
            }

            return sInstance as AccountRepository
        }
    }

    override fun insert(account : Account): Int {
        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID, account.getUserId())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE, account.getAccountBalance())
        insertValues.put(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS, account.getCoin().getCoinInitials())

        return db.insert(DatabaseConstants.ACCOUNT.TABLE_NAME, null, insertValues).toInt()

    }

    override fun select(userId : String): MutableList<Account> {
        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = "SELECT * FROM ${DatabaseConstants.ACCOUNT.TABLE_NAME} WHERE ${DatabaseConstants.ACCOUNT.COLUMNS.USER_ID} == $userId"

        val list = mutableListOf<Account>()
        val cursor = db.rawQuery(querySQL, null)

        while (cursor.moveToNext()){
            val userId = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.USER_ID))
            val balance = cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.BALANCE))
            val coin = cursor.getString(cursor.getColumnIndex(DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS))

            var coinReference : Coin = BRLCoin ()

            when (coin){
                BaseConstants.BITCOIN_ACCOUNT -> {coinReference = BTCoin()}
                BaseConstants.BRITA_ACCOUNT-> {coinReference = BritaCoin ()}
            }

            val item = Account(userId, coinReference, balance)

        list.add(item)
    }
        return list
    }

}