package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.auth.AuthBusiness
import br.com.samilaruane.carteiravirtual.domain.auth.AuthGateway
import br.com.samilaruane.carteiravirtual.domain.entities.User
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AuthBusinessTest {

    private lateinit var authBusiness : AuthBusiness
    private lateinit var gateway: AuthGateway

    @Before
    fun config (){
        gateway = Mockito.mock (AuthGateway :: class.java)
        authBusiness = AuthBusiness (gateway)
    }

    @Test
    fun authenticateAnValidUser (){
        //cenario
        val validPhone  = "+55 (79) 99199-9999"
        val validPassword = "123456"
        val anUser = User(0, "Tester", "+55 (79) 99199-99999", "", "123456")

        Mockito.`when`(gateway.getUserByPhone(validPhone)).thenReturn (anUser)

        //ação
       val result = authBusiness.authenticate(validPhone, validPassword)

        //resultado
        Assert.assertThat(result, `is`(true))

    }

    @Test
    fun doNotAuthenticateAnInvalidUser () {
        //cenario
        val invalidPhone  = "+55 (79) 99199-9998"
        val validPassword = "123456"

        Mockito.`when`(gateway.getUserByPhone(invalidPhone)).thenReturn (null)

        //ação
        val result = authBusiness.authenticate(invalidPhone, validPassword)

        //resultado
        Assert.assertThat(result, `is`(false))

    }

    @Test
    fun doNotAuthenticateAnUserWithWrongPassword () {
        //cenario
        val validPhone  = "+55 (79) 99199-9999"
        val invalidPassword = "12345"
        val anUser = User(0, "Tester", "+55 (79) 99199-99999", "", "123456")


        Mockito.`when`(gateway.getUserByPhone(validPhone)).thenReturn (anUser)

        //ação
        val result = authBusiness.authenticate(validPhone, invalidPassword)

        //resultado
        Assert.assertThat(result, `is`(false))
    }

    @Test
    fun tokenMatches (){

        //cenario
        val validToken = "23354"
        Mockito.`when`(gateway.getToken()).thenReturn("23354")

        //acão
        val result = authBusiness.checkIfMatches(validToken)

        //resultado
        Assert.assertThat(result, `is`(true))
    }

    @Test
    fun tokenDoesNotMatch () {

        //cenario
        val validToken = "23356"
        Mockito.`when`(gateway.getToken()).thenReturn("23354")

        //acão
        val result = authBusiness.checkIfMatches(validToken)

        //resultado
        Assert.assertThat(result, `is`(false))
    }

}