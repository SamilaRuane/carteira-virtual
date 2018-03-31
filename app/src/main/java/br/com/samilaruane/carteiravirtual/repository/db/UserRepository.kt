package br.com.samilaruane.carteiravirtual.repository.db

import android.content.ContentValues
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by samila on 25/12/17.
 */
@Singleton
class UserRepository @Inject constructor(dbHelper: WalletDatabaseHelper) : Repository <User>{

     val mWalletDatabaseHelper : WalletDatabaseHelper = dbHelper

    override fun create(item: User) : Long {
        val db = mWalletDatabaseHelper.writableDatabase

        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.USER.COLUMNS.NAME, item.name)
        insertValues.put(DatabaseConstants.USER.COLUMNS.EMAIL, item.email)
        insertValues.put(DatabaseConstants.USER.COLUMNS.PHONE, item.phone)
        insertValues.put(DatabaseConstants.USER.COLUMNS.PASSWORD, item.password)

        return db.insert(DatabaseConstants.USER.TABLE_NAME, null, insertValues)

    }

    override fun delete(item: User) {
    }

    override fun update(item: User) {
        val db = mWalletDatabaseHelper.writableDatabase

        val insertValues = ContentValues()
        insertValues.put(DatabaseConstants.USER.COLUMNS.NAME, item.name)
        insertValues.put(DatabaseConstants.USER.COLUMNS.EMAIL, item.email)
        insertValues.put(DatabaseConstants.USER.COLUMNS.PHONE, item.phone)
        insertValues.put(DatabaseConstants.USER.COLUMNS.PASSWORD, item.password)

        db.update(DatabaseConstants.USER.TABLE_NAME,  insertValues,"id=${item.id}", null)
    }

    override fun select(statment: Statment): List<User> {

        val db = mWalletDatabaseHelper.writableDatabase
        val querySQL = StringBuilder(statment.getQuery())

        val list = mutableListOf<User>()
        val cursor = db.rawQuery(querySQL.toString(), null)

        while (cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.ID))
            val phone = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.PHONE))
            val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.NAME))
            val email = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.EMAIL))
            val password = cursor.getString(cursor.getColumnIndex(DatabaseConstants.USER.COLUMNS.PASSWORD))

            val item = User(id, name, phone, email, password)
            list.add(item)
        }

        return list
    }
}