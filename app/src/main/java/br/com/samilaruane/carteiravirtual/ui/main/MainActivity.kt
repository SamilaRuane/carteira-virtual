package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.ui.adapters.TabsPagerAdapter
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionActivity
import br.com.samilaruane.carteiravirtual.utils.Dialog
import br.com.samilaruane.carteiravirtual.utils.NeutralDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity(), MainContract.View {

    lateinit var presenter: MainContract.Presenter
    val accountsExtract = AccountsExtract()
    val accountDetails = AccountDetailsFragment()
    val userProfile = UserProfileFragment()

    companion object {
        fun start(callerActivity: BaseActivity) {
            //(Intent (callerActivity, MainActivity :: class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()
        presenter.attachView(this)

        initViews()


    }

    override fun initViews() {


        /****** Tabs Config ********/
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_account_balance_black_24dp))
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_account_balance_wallet_black_24dp))
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_person_black_24dp))

        /******* Add Tabs *********/
        val pageAdapter = TabsPagerAdapter(supportFragmentManager)
        pageAdapter.addFragment(accountDetails)
        pageAdapter.addFragment(accountsExtract)
        pageAdapter.addFragment(userProfile)

        main_view_page.adapter = pageAdapter
        main_view_page.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tabs))
        main_tabs.setOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(main_view_page))

        fbtn_new_transaction.setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }

        if (toolbar != null) {
            // toolbar.title = getString(R.string.app_name)
            setSupportActionBar(toolbar)
        }

    }

    override fun onResumeFragments() {
        presenter.loadAccounts(accountDetails)
        presenter.loadTransactions(accountsExtract)
        presenter.getUserInfo(userProfile)
        super.onResumeFragments()
    }

    override fun showError(error: String) {
        val dialog: Dialog = NeutralDialog()
        dialog.show(this, error)
    }

}
