package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.User

/**
 * Created by samila on 14/03/18.
 */
class CredencialsManager {

    companion object {
        fun authenticateUser(user: User, password: String): Boolean {
            if (user.password == password) {
                return true
            }
            return false
        }
    }
}