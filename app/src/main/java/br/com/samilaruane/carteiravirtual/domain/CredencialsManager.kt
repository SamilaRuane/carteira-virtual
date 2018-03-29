package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper

/**
 * Created by samila on 14/03/18.
 */
class CredencialsManager {

    companion object {
        fun authenticateUser(user: User, password: String): Boolean {
            if (user.password.equals(password)) {
                return true
            }
            return false
        }
    }
}