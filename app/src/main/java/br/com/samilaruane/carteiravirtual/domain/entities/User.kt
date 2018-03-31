package br.com.samilaruane.carteiravirtual.domain.entities

/**
 * Created by samila on 22/12/17.
 */
data class User (val id : Long, var name : String,
                 var phone : String, var email : String,
                 var password: String ){

    fun isValid() : Boolean {
        return !name.isNullOrEmpty() && isEmailValid() && isPhoneValid() && !password.isNullOrEmpty()
    }

    private fun isEmailValid () : Boolean {
        return email.isNotEmpty()
    }

    private fun isPhoneValid () : Boolean {
        return phone.isNotEmpty()
    }
}