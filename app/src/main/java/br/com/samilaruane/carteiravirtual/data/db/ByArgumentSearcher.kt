package br.com.samilaruane.carteiravirtual.data.db

/**
 * Created by samila on 14/03/18.
 */
class ByArgumentSearcher (private val tableName:String,
                          private val argumentColumn:String,
                          private val argument:String): Statment {

    override fun getQuery(): String {
        return "SELECT * FROM $tableName WHERE $argumentColumn = \"$argument\""
    }
}