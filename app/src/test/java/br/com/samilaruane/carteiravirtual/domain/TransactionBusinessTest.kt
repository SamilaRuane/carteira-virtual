package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Coin
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionBusiness
import br.com.samilaruane.carteiravirtual.domain.transaction.TransactionGateway
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TransactionBusinessTest {

    private lateinit var gateway: TransactionGateway
    private lateinit var anUser: User

    @Before
    fun config() {
        gateway = Mockito.mock(TransactionGateway::class.java)
        anUser = User(0, "Usuário de teste", "+55(21)98995-3332", "tester@gmail.com", "123456")
    }

    @Test
    fun shouldBuyBritaCoin() {

        //cenario

        val classUnderTest = TransactionBusiness(gateway)

        val sourceCoin = Coin(BaseConstants.BRL, 1.0, 1.0)
        val destinationCoin = Coin(BaseConstants.BRITA, 2.0, 2.0)

        val sourceAccount = Account(0, anUser.id, sourceCoin, 10.0)
        val destinationAccount = Account(1, anUser.id, destinationCoin, 10.0)

        // ação
        val result = classUnderTest.process(
                BaseConstants.BUY, sourceAccount,
                destinationAccount, 2.0)

        //verificação

        Assert.assertTrue(result)
        Assert.assertThat(sourceAccount.getAccountBalance(), `is`(6.0))
        Assert.assertThat(destinationAccount.getAccountBalance(),  `is`(12.0))


    }


    @Test
    fun shouldSellBritaCoin() {

        //cenario

        val classUnderTest = TransactionBusiness(gateway)

        val sourceCoin = Coin(BaseConstants.BRITA, 2.0, 2.0)
        val destinationCoin = Coin(BaseConstants.BRL, 1.0, 1.0)

        val sourceAccount = Account(0, anUser.id, sourceCoin, 10.0)
        val destinationAccount = Account(1, anUser.id, destinationCoin, 10.0)

        // ação
        classUnderTest.process(
                BaseConstants.SELL, sourceAccount,
                destinationAccount, 2.0)

        //verificação

        Assert.assertThat(sourceAccount.getAccountBalance(), `is`(8.0))
        Assert.assertThat(destinationAccount.getAccountBalance(), `is`(14.0))


    }

    @Test
    fun shouldTradeBritaByBitcoin() {

        //cenario

        val classUnderTest = TransactionBusiness(gateway)

        val sourceCoin = Coin(BaseConstants.BRITA, 2.0, 2.0)
        val destinationCoin = Coin(BaseConstants.BITCOIN, 4.0, 4.0)

        val sourceAccount = Account(0, anUser.id, sourceCoin, 10.0)
        val destinationAccount = Account(1, anUser.id, destinationCoin, 10.0)

        // ação
        classUnderTest.process(
                BaseConstants.TRADE, sourceAccount,
                destinationAccount, 2.0)

        //verificação

        Assert.assertThat(sourceAccount.getAccountBalance(), `is`(8.0))
        Assert.assertThat(destinationAccount.getAccountBalance(), `is`(11.0))


    }
}