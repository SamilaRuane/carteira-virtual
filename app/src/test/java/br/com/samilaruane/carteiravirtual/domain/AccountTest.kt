package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.domain.exceptions.InsufficientBalanceException
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by samila on 18/12/17.
 */


class AccountTest {

    @Test
    fun shouldNotWithdrawnAnAmountGraterThanAccountBalance (){
        //cenário
        val aCoin = BRLCoin()
        val user = User()
        val anBRLAccount = Account(user, aCoin)

        //ação

        anBRLAccount.deposit(1200.00)

        try{
            anBRLAccount.withdraw(2000.00)
        }catch (e : InsufficientBalanceException){
            //assertThat(e.message, "Saldo Insuficiente")
        }

        // Verificação

//        Assert.assertFalse(isSuccessfullyWithdrawFromAccount)



    }

    @Test
    fun shouldTakeAnAmountFromBRLAccountAndDepositInABTCAccount (){
        //cenário
        val btCoin = BTCoin()
        val user = User()
        val BRLAccount = Account(user, btCoin)
        val BTCAccount = Account(user, btCoin)
        val amount = 1.0
        val transactionTo = TransactionAlternativeImpl()
        //ação

        BRLAccount.deposit(1200.00)
        transactionTo.takeFrom(BRLAccount).
                anAmountOf(amount).
                toBuy(btCoin).
                thenDepositIn(BTCAccount).commit()


//        val isSuccessfullyWithdrawFromAccount = BRLAccount.withdraw(2000.00)
        val qnt = BTCAccount.getAccountBalance()
        val qnt2 = BRLAccount.getAccountBalance()

        // Verificação

        assertEquals(qnt, 1.0, 0.01)
        assertEquals(qnt2, 1199.0, 0.01)
    }

    @Test
    fun shouldTakeAnAmountInBTCAccountAndDepositInBRLAccount (){
        //cenário
        val btCoin = BTCoin()
        val user = User()
        val BRLAccount = Account(user, btCoin)
        val BTCAccount = Account(user, btCoin)
        val amount = 1.0
        val transactionTo = TransactionAlternativeImpl()
        //ação

        BRLAccount.deposit(1200.00)
        BTCAccount.deposit(1.00)
        transactionTo.takeFrom(BTCAccount).
                anAmountOf(amount).
                toSell(btCoin).
                thenDepositIn(BRLAccount).commit()


//        val isSuccessfullyWithdrawFromAccount = BRLAccount.withdraw(2000.00)
        val qnt = BTCAccount.getAccountBalance()
        val qnt2 = BRLAccount.getAccountBalance()

        // Verificação

        assertEquals(qnt, 0.0, 0.01)
        assertEquals(qnt2, 1201.0, 0.01)
    }

}