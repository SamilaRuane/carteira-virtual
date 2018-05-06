package br.com.samilaruane.carteiravirtual.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.entities.Transaction
import br.com.samilaruane.carteiravirtual.extension.inflate
import br.com.samilaruane.carteiravirtual.ui.adapters.AccountExtractAdapter
import br.com.samilaruane.carteiravirtual.utils.DataCallback
import kotlinx.android.synthetic.main.fragment_account_extract.*


/**
 * Created by samila on 20/12/17.
 */

class ExtractFragment : Fragment(), DataCallback<List<Transaction>> {

    private lateinit var transactions: List<Transaction>
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (transactions == null) transactions = ArrayList()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_account_extract)
    }

    override fun onResume() {
        super.onResume()
        val adapter = AccountExtractAdapter(transactions)
        recycler_account_extract?.adapter = adapter
        recycler_account_extract?.layoutManager = LinearLayoutManager(activity)
        if (transactions.isNotEmpty() && transactions != null) {
            recycler_account_extract.visibility = View.VISIBLE
            transaction_empty_view.visibility = View.GONE
        } else {
            recycler_account_extract.visibility = View.GONE
           // txt_transaction.visibility = View.VISIBLE
            transaction_empty_view.visibility = View.VISIBLE
        }
    }

    override fun onSuccess(obj: List<Transaction>) {
        transactions = obj
    }
}