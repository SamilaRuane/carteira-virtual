package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBusiness
import br.com.samilaruane.carteiravirtual.domain.user.UserGateway
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UserBusinessTest {

    private lateinit var gateway : UserGateway
    private lateinit var userBusiness : UserBoundary

    @Before
    fun config () {
        gateway = Mockito.mock(UserGateway :: class.java)
        userBusiness = UserBusiness (gateway)
    }

    @Test
    fun shoudCreateAnUser () {
        //cenario
            val anUser = User(0, "Usuário de Teste", "+55(78)99134-5678", "user@gmail.com", "123456")
            Mockito.`when`(gateway.create(anUser)).thenReturn(1)

        //ação
       val result =  userBusiness.create(anUser)

        //verificação
        Assert.assertTrue(result > 0)
    }

    fun shoudNotCreateAnUser () {
        //cenario
        val anUser = User(0, "Usuário de Teste", "+55(78)99134-5678", "", "123456")
        Mockito.`when`(gateway.create(anUser)).thenReturn(1)

        //ação
        val result =  userBusiness.create(anUser)

        //verificação
        Assert.assertTrue(result > 0)
    }

    @Test
    fun shouldEditAnUser () {

        //cenario
        val anUser = User(0, "Usuário de Teste", "+55(78)99134-5678", "email@gmail.com", "123456")
        Mockito.`when`(gateway.edit(anUser)).thenReturn(true)
        Mockito.`when`(gateway.getUserBy(anUser.phone)).thenReturn(anUser)

        //ação
        val result =  userBusiness.update(anUser)

        //verificação
        assertThat(result, `is`(true))
    }

    @Test
    fun shouldNotEditAnUserWithBlankEmail () {

        //cenario
        val anUser = User(0, "Usuário de Teste", "+55(78)99134-5678", "", "123456")
        Mockito.`when`(gateway.edit(anUser)).thenReturn(true)
        Mockito.`when`(gateway.getUserBy(anUser.phone)).thenReturn(anUser)

        //ação
        val result =  userBusiness.update(anUser)

        //verificação
        assertThat(result, `is`(false))
    }

    @Test
    fun shouldNotEditAnUserThatNoExists () {

        //cenario
        val anUserThatNoExists = User(0, "Usuário de Teste", "+55(78)99989-5678", "email@gmail.com", "123456")
        Mockito.`when`(gateway.edit(anUserThatNoExists)).thenReturn(true)
        Mockito.`when`(gateway.getUserBy(anUserThatNoExists.phone)).thenReturn(null)

        //ação
        val result =  userBusiness.update(anUserThatNoExists)

        //verificação
        assertThat(result, `is`(false))
    }

    @Test
    fun shouldCheckThatUserExists () {
        //cenario
        val anUser = User(0, "Usuário de Teste", "+55(78)99664-5678", "email@gmail.com", "123456")
        Mockito.`when`(gateway.getUserBy(anUser.phone)).thenReturn(anUser)

        //ação
        val result =  userBusiness.userExists("+55(78)99664-5678")

        //verificação
        assertEquals(result, true)
    }

    @Test
    fun shouldCheckThatUserNoExists () {
        //cenario
        val notFoundNumber = "+55(78)99987-4563"
        Mockito.`when`(gateway.getUserBy(notFoundNumber)).thenReturn(null)

        //ação
        val result =  userBusiness.userExists(notFoundNumber)

        //verificação
        assertEquals(result, false)
    }

    @Test
    fun shouldGetCurrentUser () {
        //cenario
        val currentUser = User(0, "Usuário de Teste", "+55(78)99989-5678", "email@gmail.com", "123456")
        Mockito.`when`(gateway.getCurrent()).thenReturn(currentUser)

        //ação
        val result = userBusiness.getCurrent()

        //verificação
        assertTrue(result == currentUser)

    }

}