package br.com.samilaruane.carteiravirtual.repository.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.com.samilaruane.carteiravirtual.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.constants.DatabaseConstants
import br.com.samilaruane.carteiravirtual.domain.*

/**
 * Created by samila on 25/12/17.
 */
class UserRepositoryImpl private constructor(ctx : Context) : UserRepository{

    var mWalletDatabaseHelper : WalletDatabaseHelper = WalletDatabaseHelper( ctx )

    companion object {
        var sInstance: UserRepository? = null

        fun getInstance (ctx : Context) : UserRepository{
            if (sInstance == null){
                sInstance = UserRepositoryImpl (ctx)
            }

            return sInstance as UserRepository
        }
    }

    override fun insert (user : User) : Long{
        val db = mWalletDatabaseHelper.writableDatabase

        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.USER.COLUMNS.NAME, user.name)
        insertValues.put(DatabaseConstants.USER.COLUMNS.EMAIL, user.email)
        insertValues.put(DatabaseConstants.USER.COLUMNS.PHONE, user.phone)
        insertValues.put(DatabaseConstants.USER.COLUMNS.PASSWORD, user.password)

        return db.insert(DatabaseConstants.USER.TABLE_NAME, null, insertValues)
    }

    override fun select(query: String): MutableList<User> {
        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = StringBuilder("SELECT * FROM ${DatabaseConstants.USER.TABLE_NAME} ")

        if (!query.equals("") && query != null){
            querySQL.append(query)
        }

        val list = mutableListOf<User>()
        val cursor = db.rawQuery(querySQL.toString(), null)

        while (cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.ID))
            val phone = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.PHONE))
            val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.NAME))
            val email = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.EMAIL))
            val password = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.PASSWORD))

            val item = User (id, name,phone, email, password)
            list.add(item)
            }

        return list
    }

    override fun selectUserByPhone(query: String): User? {
        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = StringBuilder("SELECT * FROM ${DatabaseConstants.USER.TABLE_NAME} ")
        var item : User? = null

        if (!query.equals("") && query != null){
            querySQL.append("WHERE ${DatabaseConstants.USER.COLUMNS.PHONE} = '"+ query + "';")
        }
        val cursor = db.rawQuery(querySQL.toString(), null)

        while (cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.ID))
            val phone = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.PHONE))
            val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.NAME))
            val email = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.EMAIL))
            val password = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.PASSWORD))
             item = User (id, name,phone, email, password)
        }


        return item
    }
}