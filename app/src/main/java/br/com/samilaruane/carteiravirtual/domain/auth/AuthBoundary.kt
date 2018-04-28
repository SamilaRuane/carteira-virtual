package br.com.samilaruane.carteiravirtual.domain.auth

interface AuthBoundary {
    fun sendToken (phoneNumber: String)
    fun authenticate (phone : String, password : String) : Boolean
    fun checkIfMatches (token : String) : Boolean
    fun getToken () : String
    fun exit()
}