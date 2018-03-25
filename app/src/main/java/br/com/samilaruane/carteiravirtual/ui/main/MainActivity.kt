package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.dependencies.components.DaggerMainComponent
import br.com.samilaruane.carteiravirtual.dependencies.modules.MainModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.ui.adapters.TabsPagerAdapter
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.transaction.TransactionActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    val accountsExtract = AccountsExtractFragment()
    val accountDetails = AccountDetailsFragment()
    val userProfile = UserProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initDependencies()
        initViews()
    }

    override fun initViews() {

        /****** Tabs Config ********/
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_account_balance_black_24dp))
        main_tabs.addTab(main_tabs.newTab().setIcon(R.drawable.ic_extract))
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
    }

    override fun onResumeFragments() {
        presenter.loadAccounts(accountDetails)
        presenter.loadTransactions(accountsExtract)
        presenter.getUserInfo(userProfile)
        super.onResumeFragments()
    }

    override fun showError(error: String) {
      alert(error, null)
    }

    override fun initDependencies() {
        DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .appComponent(component())
                .build()
                .inject(this)
    }
}
