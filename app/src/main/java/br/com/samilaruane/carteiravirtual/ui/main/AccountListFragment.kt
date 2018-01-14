package br.com.samilaruane.carteiravirtual.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.*
import br.com.samilaruane.carteiravirtual.adapters.AccountListAdapter


/**
 * Created by samila on 20/12/17.
 */
class AccountListFragment : Fragment (){

    lateinit var accountList : MutableList<Account>


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_account_list, container, false)
        val account = Account(0, BRLCoin())
       accountList = mutableListOf(account)
        val adapter = AccountListAdapter (activity, accountList)
        val recycler = view?.findViewById<RecyclerView>(R.id.recycler_account_list)
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(activity)
        return view
    }
}