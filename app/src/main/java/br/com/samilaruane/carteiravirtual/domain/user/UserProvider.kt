package br.com.samilaruane.carteiravirtual.domain.user

import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.data.db.Repository
import br.com.samilaruane.carteiravirtual.data.db.SearchFilter
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.constants.DatabaseConstants
import javax.inject.Inject

class UserProvider @Inject constructor(val repository: Repository<User>, val preferences : SharedPreferencesHelper) : UserGateway{

    override fun create(user: User) : Long  = repository.create(user)

    override fun edit(user: User) : Boolean = repository.update(user) != null

    override fun getCurrent(): User = repository.select(SearchFilter.getById(
            DatabaseConstants.USER.TABLE_NAME,"id",
            preferences.getUserId().toString())).single()

    override fun getUserBy(phone: String) : User? = repository.select(SearchFilter
                    .getByArgument(DatabaseConstants.USER.TABLE_NAME,
                    "phone", phone)).singleOrNull()

}