package br.com.samilaruane.carteiravirtual.domain.entities

import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test


/**
 * Created by samila on 18/12/17.
 */


class AccountTest {

    private lateinit var aCoin: Coin

    @Before
    fun config (){
        aCoin = Coin(BaseConstants.BRL_ACCOUNT, 1.0, 1.0)
    }
    @Test (expected = InsufficientBalanceException :: class)
    fun shouldNotWithdrawnAnAmountGraterThanAccountBalance () {
        //cenário
        val target = Account(0, 0, aCoin, 0.0)

        //ação

        target.withdraw(2000.00)
    }

    @Test
    fun shouldDepositAmountInTheAccount () {
        //cenário
        val target = Account(0, 0, aCoin, 0.0)

        //ação
        target.deposit(2000.00)

        //Verificação
        assertThat(target.getAccountBalance(), `is`(2000.00))
    }

    @Test
    fun shouldNotAcceptNegativeInputsOnDeposit (){
        //cenário
        val target = Account(0, 0, aCoin, 0.0)

        //ação
        target.deposit(-2000.00)

        //Verificação
        assertThat(target.getAccountBalance(), CoreMatchers.not(-2000.00))
    }

    @Test
    fun shouldNotAcceptNegativeInputsOnWithdraw (){
        //cenário
        val target = Account(0, 0, aCoin, 0.0)

        //ação
        target.withdraw(-2000.00)

        //Verificação
        assertThat(target.getAccountBalance(), not(2000.00))
    }

    @Test
    fun shouldReturnCoinInitials () {

        //cenário
        val target = Account(0, 0, aCoin, 0.0)

        //ação
        val initials = target.getCoin().name

        //Verificação

        assertThat(initials, `is`(BaseConstants.BRL_ACCOUNT))
    }

}