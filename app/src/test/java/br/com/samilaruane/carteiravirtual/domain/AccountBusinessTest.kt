package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.account.AccountBusiness
import br.com.samilaruane.carteiravirtual.domain.account.AccountGateway
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Coin
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AccountBusinessTest {

    private lateinit var gateway: AccountGateway
    private lateinit var anUser: User

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Before
    fun config() {
        gateway = Mockito.mock(AccountGateway::class.java)
        anUser = User(0, "Usuário de Teste", "+55(21)99898-4543", "user@gmail.com", "123456")
    }

    @Test
    fun shouldCreateAccountsToAnUser() {

        //cenario
        val classUnderTest = AccountBusiness(gateway)

        Mockito.`when`(gateway.create(any())).thenReturn(1)

        //ação
        val result = classUnderTest.createAccountsTo(anUser.id)

        //verificação
        Assert.assertThat(result, CoreMatchers.`is`(true))
    }

    @Test
    fun shouldEditAnAccount() {
        //cenario
        val classUnderTest = AccountBusiness(gateway)
        val aCoin = Coin(BaseConstants.BRL, 1.0, 1.0)
        val anAccount = Account(0, anUser.id, aCoin)

        Mockito.`when`(gateway.edit(anAccount)).thenReturn(true)

        //ação
        val result = classUnderTest.edit(anAccount)

        //verificação
        Assert.assertTrue(result)
    }

    @Test
    fun shouldReturnUserAccounts() {
        //cenario
        val classUnderTest = AccountBusiness(gateway)
        val brlCoin = Coin(BaseConstants.BRL, 1.0, 1.0)
        val britaCoin = Coin(BaseConstants.BRITA, 2.0, 2.0)
        val bitCoin = Coin(BaseConstants.BITCOIN, 2.0, 2.0)
        val aBritaAccount = Account(0, anUser.id, britaCoin)
        val aBitcoinAccount = Account(1, anUser.id, bitCoin)
        val aBrlAccount = Account(2, anUser.id, brlCoin)
        val accountList = arrayListOf(aBritaAccount, aBrlAccount, aBitcoinAccount)

        Mockito.`when`(gateway.get(anUser.id)).thenReturn(accountList)

        //ação
        val result = classUnderTest.getFrom(anUser.id)

        //verificação
        Assert.assertThat(result, CoreMatchers.isA(List::class.java))
        Assert.assertTrue(result.size == 3)
        Assert.assertThat(result[0].getUserId(), CoreMatchers.`is`(anUser.id))
        Assert.assertThat(result[1].getUserId(), CoreMatchers.`is`(anUser.id))
        Assert.assertThat(result[2].getUserId(), CoreMatchers.`is`(anUser.id))

    }

    @Test
    fun shouldReturnAnAccountByCoin() {
        //cenario
        val classUnderTest = AccountBusiness(gateway)
        val brlCoin = Coin(BaseConstants.BRL, 1.0, 1.0)
        val britaCoin = Coin(BaseConstants.BRITA, 2.0, 2.0)
        val bitCoin = Coin(BaseConstants.BITCOIN, 3.0, 3.0)
        val aBritaAccount = Account(0, anUser.id, britaCoin)
        val aBitcoinAccount = Account(1, anUser.id, bitCoin)
        val aBrlAccount = Account(2, anUser.id, brlCoin)
        val accountList = arrayListOf(aBritaAccount, aBrlAccount, aBitcoinAccount)


        //ação
        val brlResult = classUnderTest.getAccount(BaseConstants.BRL, accountList)
        val britaResult = classUnderTest.getAccount(BaseConstants.BRITA, accountList)
        val bitcoinResult = classUnderTest.getAccount(BaseConstants.BITCOIN, accountList)

        //verificação
        Assert.assertThat(brlResult?.getCoin()?.name, CoreMatchers.`is`(BaseConstants.BRL))
        Assert.assertThat(britaResult?.getCoin()?.name, CoreMatchers.`is`(BaseConstants.BRITA))
        Assert.assertThat(bitcoinResult?.getCoin()?.name, CoreMatchers.`is`(BaseConstants.BITCOIN))
    }

}