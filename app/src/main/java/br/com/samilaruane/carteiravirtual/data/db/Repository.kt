package br.com.samilaruane.carteiravirtual.data.db

/**
 * Created by samila on 14/03/18.
 */
interface Repository <T>{

    fun create(item : T) : Long
    fun delete (item : T)
    fun update (item : T)
    fun select (statment : Statment) : List <T>
}