package br.com.samilaruane.carteiravirtual.domain.user

import br.com.samilaruane.carteiravirtual.domain.entities.User
import javax.inject.Inject

class UserBusiness @Inject constructor(val gateway: UserGateway) : UserBoundary {

    override fun create(user: User): Long {
        var result: Long = 0
        if(user.isValid())
            result = gateway.create(user)

        return  result
    }

    override fun changePassword(user: User): Boolean = gateway.edit(user)

    override fun userExists(phone: String): Boolean = gateway.getUserBy(phone) != null

    override fun getUser(phone: String): User? = gateway.getUserBy(phone)

    override fun getCurrent(): User = gateway.getCurrent()

    override fun update(user: User): Boolean  {
        var result = false

        if(user.isValid() && userExists(user.phone))
            result = gateway.edit(user)

        return  result
    }
}