package br.com.samilaruane.carteiravirtual.domain.auth

import br.com.samilaruane.carteiravirtual.domain.entities.User

interface AuthGateway {
    fun getUserByPhone (number : String) : User?
    fun saveCurrentUser (user : User)
    fun invalidateSavedUser ()
    fun keepToken (number : String, token : String)
    fun getToken () : String
}