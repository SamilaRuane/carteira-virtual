package br.com.samilaruane.carteiravirtual.domain.user

import br.com.samilaruane.carteiravirtual.domain.entities.User

interface UserGateway {
    fun create (user : User) : Long
    fun edit (user : User) : Boolean
    fun getCurrent () : User
    fun getUserBy(phone : String) : User?
}