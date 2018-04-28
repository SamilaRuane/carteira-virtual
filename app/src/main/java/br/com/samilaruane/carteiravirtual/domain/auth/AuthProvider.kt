package br.com.samilaruane.carteiravirtual.domain.auth

import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.data.db.Repository
import br.com.samilaruane.carteiravirtual.data.db.SearchFilter
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants

class AuthProvider(private val repository: Repository<User>, private val preferences: SharedPreferencesHelper) : AuthGateway {

    override fun getUserByPhone(number: String): User? =
            repository.select(SearchFilter.getByArgument(DatabaseConstants.USER.TABLE_NAME,
                    DatabaseConstants.USER.COLUMNS.PHONE, number)).singleOrNull()

    override fun saveCurrentUser(user: User) {
        preferences.saveUserId(user)
        preferences.setIsAuth(true)
    }

    override fun invalidateSavedUser() {
        preferences.saveUserId(User(1, "", "", "", ""))
        preferences.setIsAuth(false)
    }

    override fun keepToken(number: String, token: String) {
        preferences.keepTokenForConfirmation(number, token)
    }

    override fun getToken(): String {
        return preferences.getToken()
    }
}