package br.com.samilaruane.carteiravirtual.domain.entities

/**
 * Created by samila on 22/12/17.
 */
data class User (val id : Long, val name : String,
                 val phone : String, val email : String,
                 var password: String ){

    fun isValid() : Boolean {
        return !name.isNullOrEmpty() && isEmailValid() && isPhoneValid() && !password.isNullOrEmpty()
    }

    private fun isEmailValid () : Boolean {
        //TODO create and call a class that validates the email with the specific rules
        return email.isNotEmpty()
    }

    private fun isPhoneValid () : Boolean {
        // TODO create and call a class that validates the phone with the specific rules
        return phone.isNotEmpty()
    }
}