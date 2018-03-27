package br.com.samilaruane.carteiravirtual.domain


import br.com.samilaruane.carteiravirtual.domain.entities.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.repository.remote.Service
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by samila on 25/03/18.
 */

class CoinTest {

    @Test
    fun shouldReturnQuotation()  {
        //cenário
        val service : Service<*>? = Mockito.mock(Service  :: class.java)
        val listener = Mockito.mock(EventResponseListener :: class.java)

        val aCoin  = BritaCoin(service as Service<BancoCentralResponse>)

        //service.when()

        //ação
       // val salePrice : Double = aCoin.getSalePrice(listener as EventResponseListener<Double>)

        //Verificação


    }
}
