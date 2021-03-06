package br.com.samilaruane.carteiravirtual.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.extension.dayOfWeek
import br.com.samilaruane.carteiravirtual.extension.formatter
import br.com.samilaruane.carteiravirtual.extension.roundTo
import br.com.samilaruane.carteiravirtual.utils.DataCallback
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import kotlinx.android.synthetic.main.fragment_main.*
import org.json.JSONObject
import java.util.*

/**
 * Created by samila on 20/12/17.
 */
class MainFragment : Fragment(), DataCallback<List<Account>> {

    private lateinit var accounts: List<Account>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (accounts == null) accounts = ArrayList()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (!accounts.isEmpty()) {
            txt_brita_account_balance.text = accounts[1].getAccountBalance().roundTo(2).toString()
            txt_brita_coin_initials.text = accounts[1].getCoin().name
            txt_bitcoin_account_balance.text = accounts[2].getAccountBalance().roundTo(2).toString()
            txt_bitcoin_coint_initials.text = accounts[2].getCoin().name
            txt_brl_account_balance.text = accounts[0].getAccountBalance().roundTo(2).toString()
            //txt_brl_initials.text = accounts[0].getCoin().name


            val date = Calendar.getInstance()
            today.text = date.formatter("dd")
            this_month.text = date.formatter("MMM")
            this_year.text = date.formatter("yyyy")
            day_of_week.text = date.dayOfWeek()

            update()
        }
    }

    override fun onSuccess(obj: List<Account>) {
        accounts = obj
    }

    fun update() {
        val preferences = SharedPreferencesHelper(this.activity)
        if (preferences.getBitcoinQuotation().isNotEmpty() && preferences.getBritaQuotation().isNotEmpty()) {
            txt_brita_salePrice.text = JSONObject(preferences.getBritaQuotation()).get(BaseConstants.SALE_PRICE).toString().toDouble().roundTo(2).toString()
            txt_brita_purchase_quot.text = JSONObject(preferences.getBritaQuotation()).get(BaseConstants.PURCHASE_QUOTATION).toString().toDouble().roundTo(2).toString()
            txt_bitcoin_salePrice.text = JSONObject(preferences.getBitcoinQuotation()).get(BaseConstants.SALE_PRICE).toString().toDouble().roundTo(2).toString()
            txt_bitcoin_purchase_quot.text = JSONObject(preferences.getBitcoinQuotation()).get(BaseConstants.PURCHASE_QUOTATION).toString().toDouble().roundTo(2).toString()
        }
    }
}