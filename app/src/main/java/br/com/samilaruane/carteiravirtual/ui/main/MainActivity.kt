package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.entities.DollarExchangeRate
import br.com.samilaruane.carteiravirtual.adapters.TabsPagerAdapter
import br.com.samilaruane.carteiravirtual.repository.remote.RetrofitInitializer
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionActivity
import br.com.samilaruane.carteiravirtual.utils.Dialog
import br.com.samilaruane.carteiravirtual.utils.ErrorDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        fbtn_new_transaction.setOnClickListener {
            startActivity(Intent(this, TransactionActivity :: class.java))
        }
    }

    override fun initViews() {
        /***** Create Fragments ****/
        val accountDetails = AccountDetailsFragment()
        val accountList = AccountListFragment()
        val userProfile = UserProfileFragment()

        setSupportActionBar(toolbar)


        /****** Tabs Config ********/
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_attach_money_black_24dp))
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_account_balance_wallet_black_24dp))
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_person_black_24dp))

        /******* Add Tabs *********/
        val pageAdapter = TabsPagerAdapter(supportFragmentManager)
        pageAdapter.addFragment(accountDetails)
        pageAdapter.addFragment(accountList)
        pageAdapter.addFragment(userProfile)

        main_view_page.adapter = pageAdapter
        main_view_page.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tabs))
        main_tabs.setOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(main_view_page))

    }

    override fun showError(error: String) {
        val dialog : Dialog = ErrorDialog()
        dialog.show(this, error)
    }

}