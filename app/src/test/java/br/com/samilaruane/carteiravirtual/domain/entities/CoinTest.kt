package br.com.samilaruane.carteiravirtual.domain.entities


import org.junit.Test

/**
 * Created by samila on 25/03/18.
 */

class CoinTest {

    @Test
    fun shouldReturnPurchaseQuotation()  {
       /* //cenário
        val target : Coin = Mockito.mock(Coin :: class.java)
        Mockito.`when`(target.getPurchaseQuotation()).thenReturn(1.0)

        //ação
        val purchaseQuotation : Double = target.getPurchaseQuotation()

        //Verificação

        assertThat(purchaseQuotation, `is`(1.0))
*/
    }

    @Test
    fun shouldReturnSalePrice () {

     /*   //cenário
        val target : Coin = Mockito.mock(Coin :: class.java)
        Mockito.`when`(target.getSalePrice()).thenReturn(1.0)



        //ação
         val salePrice : Double = target.getSalePrice()

        //Verificação
        assertThat(salePrice, `is`(1.0))
    */}

    @Test
    fun shouldReturnBitcoinInitials (){
      /*  //cenário
        val target : Coin = Mockito.mock(Coin :: class.java)
        Mockito.`when`(target.getCoinInitials()).thenReturn(BaseConstants.BITCOIN_ACCOUNT)

        //ação
        val result = target.getCoinInitials()

        //Verificação
        assertThat(result, `is`(BaseConstants.BITCOIN_ACCOUNT))
    */}
}
