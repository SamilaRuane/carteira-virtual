package br.com.samilaruane.carteiravirtual.repository.db

/**
 * Created by samila on 14/03/18.
 */
class ByArgumentSearcher (val tableName:String,
                          val argumentColumn:String,
                          val argument:String): Statment {

    override fun getQuery(): String {
        return "SELECT * FROM $tableName WHERE $argumentColumn = $argument"
    }
}