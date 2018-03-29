package br.com.samilaruane.carteiravirtual.domain

import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.domain.entities.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.repository.remote.BitcoinService
import br.com.samilaruane.carteiravirtual.repository.remote.Service
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
    lateinit var service : Service<*>

    @Before
    fun setup (){
        coinUnderTest = Mockito.mock (Coin :: class.java)
        sourceAccount = Mockito.mock (Account :: class.java)
        targetAccount = Mockito.mock (Account :: class.java)
        service = Mockito.mock(Service :: class.java)
    }

    @Test
    fun shouldSellCoin (){
        //cenário
//        val bitcoin = BTCoin (service as Service<MercadoBitcoinResponse>, )
//        val brita  = BritaCoin(service as Service<BancoCentralResponse>)


       // val objectUnderTest = TransactionBusiness()
        Mockito.`when`(coinUnderTest.getSalePrice()).thenReturn(2.0)
        Mockito.`when`(coinUnderTest.getPurchaseQuotation()).thenReturn(3.0)


        //ação

    }

}