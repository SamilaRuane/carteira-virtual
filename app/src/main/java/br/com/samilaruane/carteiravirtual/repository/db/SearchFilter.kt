package br.com.samilaruane.carteiravirtual.repository.db

/**
 * Created by samila on 14/03/18.
 */
class SearchFilter  {

    companion object {

        fun getById(tableName: String, idColumn: String, id: String): Statment {
            return ByIdSearcher(tableName, idColumn, id)
        }

        fun getAll(tableName: String) : Statment {
            return AllItensSearcher(tableName)
        }

        fun getByArgument(tableName: String, argumentColumn: String, argument: String) : Statment {
            return ByArgumentSearcher(tableName, argumentColumn, argument)
        }

    }

}