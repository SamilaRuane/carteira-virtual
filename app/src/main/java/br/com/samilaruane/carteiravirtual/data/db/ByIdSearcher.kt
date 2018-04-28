package br.com.samilaruane.carteiravirtual.data.db

/**
 * Created by samila on 14/03/18.
 */
class ByIdSearcher(val tableName:String,
                   val idColumn:String,
                   val id:String): Statment {


    override fun getQuery(): String {
       return "SELECT * FROM $tableName WHERE $idColumn = $id;"
    }
}