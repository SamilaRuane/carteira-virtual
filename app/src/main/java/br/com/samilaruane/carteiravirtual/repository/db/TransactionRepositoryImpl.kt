package br.com.samilaruane.carteiravirtual.repository.db

import android.content.ContentValues
import android.content.Context
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import br.com.samilaruane.carteiravirtual.domain.*

/**
 * Created by samila on 07/01/18.
 */
class TransactionRepositoryImpl private constructor(ctx : Context) : TransactionRepository{

    val mWalletDatabaseHelper : WalletDatabaseHelper = WalletDatabaseHelper( ctx )

    companion object {
        var sInstance : TransactionRepository? = null

        fun getInstance (ctx : Context) : TransactionRepository{
            if(sInstance == null){
                sInstance = TransactionRepositoryImpl(ctx)
            }

            return sInstance as TransactionRepository
        }
    }


    override fun insert(transaction: Transaction): Int {

        val db = mWalletDatabaseHelper.writableDatabase
        val insertValues =  ContentValues ()
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.DATE, transaction.date)
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.SOURCE_ACCOUNT, transaction.sourceAccount.getId())
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.DESTINATION_ACCOUNT, transaction.destinationAccount.getId())
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.AMOUNT, transaction.amount)
        insertValues.put(DatabaseConstants.TRANSACTION.COLUMNS.TYPE, transaction.transactionType)


        return db.insert(DatabaseConstants.TRANSACTION.TABLE_NAME, null, insertValues).toInt()
    }

    override fun select(userId : String): List<Transaction> {
        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = "SELECT * FROM ${DatabaseConstants.TRANSACTION.TABLE_NAME} WHERE ${DatabaseConstants.TRANSACTION.COLUMNS.ID} == $userId"

        val list = mutableListOf<Transaction>()
        val cursor = db.rawQuery(querySQL, null)

        while (cursor.moveToNext()){
            //TODO Implementar lógica de recuperação de dados
        }
        return list
    }

}