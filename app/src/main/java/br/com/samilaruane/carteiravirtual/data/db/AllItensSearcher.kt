package br.com.samilaruane.carteiravirtual.data.db

/**
 * Created by samila on 14/03/18.
 */
class AllItensSearcher(private val tableName : String): Statment {

    override fun getQuery(): String {
        return "SELECT * FROM $tableName"
    }
}