package br.com.samilaruane.carteiravirtual.domain.entities

import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by samila on 27/03/18.
 */
class TransactionTest{

    lateinit var coinUnderTest : Coin
    lateinit var sourceAccount : Account
    lateinit var targetAccount : Account
   // lateinit var service : Service<*>

    @Before
    fun setup (){
        coinUnderTest = Coin (BaseConstants.BRL_ACCOUNT, 1.0, 1.0)
        sourceAccount = Mockito.mock (Account:: class.java)
        targetAccount = Mockito.mock (Account:: class.java)
       // service = Mockito.mock(Service :: class.java)
    }

    @Test
    fun shouldSellCoin (){
        //cenário
//        val bitcoin = BTCoin (service as Service<MercadoBitcoinResponse>, )
//        val brita  = BritaCoin(service as Service<BancoCentralResponse>)


       // val objectUnderTest = TransactionBusiness()
        Mockito.`when`(coinUnderTest.salePrice).thenReturn(2.0)
        Mockito.`when`(coinUnderTest.purchaseQuotation).thenReturn(3.0)


        //ação

    }

}