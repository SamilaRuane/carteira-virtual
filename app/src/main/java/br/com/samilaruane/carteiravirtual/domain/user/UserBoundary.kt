package br.com.samilaruane.carteiravirtual.domain.user

import br.com.samilaruane.carteiravirtual.domain.entities.User

interface UserBoundary {
    fun create (user : User) : Long
    fun update (user : User) : Boolean
    fun changePassword (user : User) : Boolean
    fun userExists (phone : String) : Boolean
    fun getUser (phone: String) : User?
    fun getCurrent () : User
}