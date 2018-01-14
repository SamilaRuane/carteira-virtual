package br.com.samilaruane.carteiravirtual.repository.db

import br.com.samilaruane.carteiravirtual.domain.User

/**
 * Created by samila on 25/12/17.
 */
interface UserRepository {

    fun insert (user : User) : Long

    fun select (query : String) : MutableList <User>

    fun selectUserByPhone (query : String) : User?
}