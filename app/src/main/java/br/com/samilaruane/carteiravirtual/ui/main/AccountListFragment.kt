package br.com.samilaruane.carteiravirtual.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.entities.Account
import br.com.samilaruane.carteiravirtual.ui.adapters.AccountListAdapter
import br.com.samilaruane.carteiravirtual.utils.OnDatabaseAccessListener


/**
 * Created by samila on 20/12/17.
 */
class AccountListFragment : Fragment (), MainContract.View, OnDatabaseAccessListener {

    lateinit var accountList : MutableList<Account>
    lateinit var presenter : MainContract.Presenter

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         presenter = MainPresenter ()
         presenter.attachView(context as MainContract.View)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_account_list, container, false)
        presenter.loadAccounts(this)
        val recycler = view?.findViewById<RecyclerView>(R.id.recycler_account_list)
        val adapter = AccountListAdapter (activity, accountList)
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSelectSuccess(dataList: List<Object>) {
        accountList = dataList as MutableList<Account>
    }
}