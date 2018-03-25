package br.com.samilaruane.carteiravirtual.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.extension.roundTo
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener
import kotlinx.android.synthetic.main.fragment_account_details.*

/**
 * Created by samila on 20/12/17.
 */
class AccountDetailsFragment : Fragment, OnDatabaseAccessListener<List<Account>> {

    constructor() : super()
    private lateinit var accounts : List <Account>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(accounts == null)  accounts = ArrayList<Account> ()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_account_details, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if(!accounts.isEmpty()) {
            txt_brita_account_balance.text = accounts[0].getAccountBalance().roundTo(2).toString()
            txt_brita_coin_initials.text = accounts[0].getCoin().getCoinInitials()
            txt_bitcoin_account_balance.text = accounts[1].getAccountBalance().roundTo(2).toString()
            txt_bitcoin_coint_initials.text = accounts[1].getCoin().getCoinInitials()
            txt_brl_account_balance.text = accounts[2].getAccountBalance().roundTo(2).toString()
            txt_brl_initials.text = accounts[2].getCoin().getCoinInitials()
        }
    }

    override fun onSelectSuccess(obj: List<Account>) {
       accounts = obj
    }
}