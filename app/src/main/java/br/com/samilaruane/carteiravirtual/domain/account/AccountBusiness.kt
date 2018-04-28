package br.com.samilaruane.carteiravirtual.domain.account

import android.util.Log
import br.com.samilaruane.carteiravirtual.data.model.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.data.model.MercadoBitcoinResponse
import br.com.samilaruane.carteiravirtual.data.remote.BitcoinService
import br.com.samilaruane.carteiravirtual.data.remote.BritaService
import br.com.samilaruane.carteiravirtual.data.remote.ServiceListener
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.domain.entities.Coin
import br.com.samilaruane.carteiravirtual.extension.*
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

class AccountBusiness @Inject constructor(val gateway: AccountGateway) : AccountBoundary, ServiceListener.Bitcoin, ServiceListener.Brita {

    private val brita = Coin(BaseConstants.BRITA_ACCOUNT, 1.0, 1.0)
    private val bitcoin = Coin(BaseConstants.BITCOIN_ACCOUNT, 1.0, 1.0)
    private val mCalendar: Calendar = Calendar.getInstance()
    private lateinit var mListener: EventResponseListener<String>

    override fun createAccountsTo(id: Long): Boolean {
        val brl = Coin(BaseConstants.BRL_ACCOUNT, 1.0, 1.0)

        return gateway.create(Account(0, id, brl, 100000.00)) > 0 &&
                gateway.create(Account(0, id, brita, 0.0)) > 0 &&
                gateway.create(Account(0, id, bitcoin, 0.0)) > 0
    }

    override fun edit(account: Account) {
        gateway.edit(account)
    }

    override fun getFrom(userId: Long): List<Account> = gateway.get(userId)

    override fun getAccount(coin: String, accounts : List <Account>) : Account? {
        var baseAccount : Account? = null
        var britaAccount : Account? = null
        var bitcoinAccount : Account? = null

        for (acc: Account in accounts) {
            when (acc.getCoin().name) {
                BaseConstants.BRITA_ACCOUNT -> britaAccount = acc
                BaseConstants.BITCOIN_ACCOUNT -> bitcoinAccount = acc
                BaseConstants.BRL_ACCOUNT -> baseAccount = acc
            }
        }

        if(britaAccount != null && bitcoinAccount != null && baseAccount != null) {
            when (coin) {
                BaseConstants.BRL_ACCOUNT -> return baseAccount
                BaseConstants.BRITA_ACCOUNT -> return britaAccount
                BaseConstants.BITCOIN_ACCOUNT -> return bitcoinAccount
            }
        }

        return null
    }

    override fun callServices(listener: EventResponseListener<String>) {

        mListener = listener

        mCalendar.addDays(-1)

        if (!mCalendar.isWorkingDay()) {
            if (mCalendar.isSaturday()) mCalendar.addDays(-1)
            else if (mCalendar.isSunday()) mCalendar.addDays(-2)
        }

        BitcoinService.getCoinQuotation(this)
        BritaService.getCoinQuotation(this, mCalendar.formatter("MM-dd-yyyy"))
    }


    override fun getQuotation (coinName : String) : Coin {
        var result = Coin(BaseConstants.BRL_ACCOUNT, 1.0, 1.0)

        when (coinName) {
            BaseConstants.BITCOIN_ACCOUNT -> result = getBitcoin()
            BaseConstants.BRITA_ACCOUNT -> result = getBrita()
        }

        return result
    }
    fun getBrita(): Coin {

        try {
            brita.purchaseQuotation = JSONObject(gateway.getBritaQuotation())
                    .get(BaseConstants.PURCHASE_QUOTATION)
                    .toString()
                    .toDouble()
            brita.salePrice = JSONObject(gateway
                    .getBritaQuotation())
                    .get(BaseConstants.SALE_PRICE)
                    .toString()
                    .toDouble()
        }catch (e : JSONException){
            Log.i("APPTRACKER", e.message)
        }
        return brita
    }

     fun getBitcoin(): Coin {

        try {
            bitcoin.purchaseQuotation = JSONObject(gateway
                    .getBitcoinQuotation())
                    .get(BaseConstants.PURCHASE_QUOTATION)
                    .toString()
                    .toDouble()

            bitcoin.salePrice = JSONObject(gateway
                    .getBitcoinQuotation())
                    .get(BaseConstants.SALE_PRICE)
                    .toString()
                    .toDouble()
        }catch (e : JSONException){
            Log.i("APPTRACKER", e.message)
        }

        return bitcoin
    }

    override fun onSuccess(response: BancoCentralResponse) {
        if (response.value.isEmpty()) {
            mCalendar.addDays(-1)
            BritaService.getCoinQuotation(this, mCalendar.formatter("MM-dd-yyyy"))
        } else {
            val map = HashMap<String, String>()
            if (response.value != null && !response.value.isEmpty()) {
                map.put(BaseConstants.SALE_PRICE, response.value[0].cotacaoVenda.toString())
                map.put(BaseConstants.PURCHASE_QUOTATION, response.value[0].cotacaoCompra.toString())
                gateway.setBritaQuotation(JSONObject(map).toString())
            }
            mListener.onSuccess("")
        }
    }

    override fun onSuccess(response: MercadoBitcoinResponse) {
        val map = HashMap<String, String>()
        map.put(BaseConstants.SALE_PRICE, response.ticker.sell.toString())
        map.put(BaseConstants.PURCHASE_QUOTATION, response.ticker.buy.toString())
        gateway.setBitcoinQuotation(JSONObject(map).toString())
        mListener.onSuccess("")
    }

    override fun onError(error: String) {
        mListener.onError(error)
    }
}