package br.com.samilaruane.carteiravirtual.repository.db

/**
 * Created by samila on 14/03/18.
 */
class AllItensSearcher(val tableName : String): Statment {

    override fun getQuery(): String {
        return "SELECT * FROM $tableName"
    }
}