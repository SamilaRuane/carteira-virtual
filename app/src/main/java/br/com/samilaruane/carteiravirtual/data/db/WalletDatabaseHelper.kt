package br.com.samilaruane.carteiravirtual.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants

/**
 * Created by samila on 25/12/17.
 */
class WalletDatabaseHelper (context: Context?) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "wallet.db"
        const val DATABASE_VERSION = 1
    }

    private val createTableUser = """
                    CREATE TABLE ${DatabaseConstants.USER.TABLE_NAME} (
                    ${DatabaseConstants.USER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                    ${DatabaseConstants.USER.COLUMNS.NAME} TEXT,
                    ${DatabaseConstants.USER.COLUMNS.EMAIL} TEXT,
                    ${DatabaseConstants.USER.COLUMNS.PHONE} TEXT,
                    ${DatabaseConstants.USER.COLUMNS.PASSWORD} TEXT
                    );
"""

    private val createTableAccount = """
                        CREATE TABLE ${DatabaseConstants.ACCOUNT.TABLE_NAME} (
                        ${DatabaseConstants.ACCOUNT.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                        ${DatabaseConstants.ACCOUNT.COLUMNS.USER_ID} INTEGER,
                        ${DatabaseConstants.ACCOUNT.COLUMNS.BALANCE} INTEGER,
                        ${DatabaseConstants.ACCOUNT.COLUMNS.COIN_INITIALS} TEXT
                        );

"""
    private val createTableTransaction = """
                        CREATE TABLE ${DatabaseConstants.TRANSACTION.TABLE_NAME} (
                        ${DatabaseConstants.TRANSACTION.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                        ${DatabaseConstants.TRANSACTION.COLUMNS.USER_ID} INTEGER,
                        ${DatabaseConstants.TRANSACTION.COLUMNS.TYPE} TEXT,
                        ${DatabaseConstants.TRANSACTION.COLUMNS.DATE} INTEGER,
                        ${DatabaseConstants.TRANSACTION.COLUMNS.AMOUNT} INTEGER,
                        ${DatabaseConstants.TRANSACTION.COLUMNS.SOURCE_ACCOUNT} INTEGER,
                        ${DatabaseConstants.TRANSACTION.COLUMNS.DESTINATION_ACCOUNT} INTEGER
                        );
"""

    /*private val createTableCoin = """
                        CREATE TABLE ${DatabaseConstants.COIN.TABLE_NAME} (
                        ${DatabaseConstants.COIN.COLUMNS.NAME} INTEGER PRIMARY KEY,
                        ${DatabaseConstants.COIN.COLUMNS.PURCHASE} INTEGER,
                        ${DatabaseConstants.COIN.COLUMNS.SALE} TEXT
                        );
"""*/

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL(createTableUser)
            it.execSQL(createTableAccount)
            it.execSQL(createTableTransaction)
//          it.execSQL(createTableCoin)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}