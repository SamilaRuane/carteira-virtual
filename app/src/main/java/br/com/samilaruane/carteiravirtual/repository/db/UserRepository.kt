package br.com.samilaruane.carteiravirtual.repository.db

import android.content.ContentValues
import android.content.Context
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import br.com.samilaruane.carteiravirtual.domain.entities.User

/**
 * Created by samila on 25/12/17.
 */
class UserRepository private constructor(ctx : Context) : Repository <User>{

    var mWalletDatabaseHelper : WalletDatabaseHelper = WalletDatabaseHelper( ctx )

    companion object {
        var sInstance: Repository<User>? = null

        fun getInstance (ctx : Context) : UserRepository{
            if (sInstance == null){
                sInstance = UserRepository(ctx)
            }

            return sInstance as UserRepository
        }
    }

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