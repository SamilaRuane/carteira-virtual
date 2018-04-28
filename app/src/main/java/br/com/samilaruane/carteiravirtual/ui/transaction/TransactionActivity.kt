package br.com.samilaruane.carteiravirtual.ui.transaction

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.di.components.DaggerTransactionComponent
import br.com.samilaruane.carteiravirtual.di.modules.TransactionModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

class TransactionActivity : AppCompatActivity(), TransactionContract.View, AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var mPresenter: TransactionContract.Presenter

    /* Activity Lifecycle */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        initDependencies()
        initViews()
        layout_progress.visibility = View.VISIBLE
        mPresenter.loadServiceData()
    }

    /* Transaction Contract */
    override fun onError(error: String) {
        layout_progress.visibility = View.GONE
        alert( error, null )
    }

    override fun initViews() {

        layout_progress.visibility = View.GONE


        spinner_transaction_type.adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                resources.getStringArray(R.array.transactiontypes))

        spinner_source_account.adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                resources.getStringArray(R.array.coins))

        spinner_destination_account.adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                resources.getStringArray(R.array.coins))

        spinner_transaction_type.onItemSelectedListener = this

        btn_save_transaction.setOnClickListener {

            if (edt_transaction_amount.text.toString().isNotEmpty()) {
                layout_progress.visibility = View.VISIBLE

                when (spinner_transaction_type.selectedItem.toString()) {
                    BaseConstants.SELL -> {
                        mPresenter.saveTransaction(spinner_transaction_type.selectedItem.toString(),
                                spinner_source_account.selectedItem.toString(),
                                BaseConstants.BRL_ACCOUNT, edt_transaction_amount.text.toString().toDouble())
                    }
                    BaseConstants.BUY -> {
                        mPresenter.saveTransaction(spinner_transaction_type.selectedItem.toString(), BaseConstants.BRL_ACCOUNT,
                                spinner_source_account.selectedItem.toString(), edt_transaction_amount.text.toString().toDouble())
                    }
                    BaseConstants.TRADE -> {
                        mPresenter.saveTransaction(spinner_transaction_type.selectedItem.toString(),
                                spinner_destination_account.selectedItem.toString(), spinner_source_account.selectedItem.toString(),
                                edt_transaction_amount.text.toString().toDouble())
                    }

                }

            } else {
                Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (spinner_transaction_type.getItemAtPosition(position) == BaseConstants.SELL ||
                spinner_transaction_type.getItemAtPosition(position) == BaseConstants.BUY) {
            txt_destination_account.visibility = View.GONE
            spinner_destination_account.visibility = View.GONE
        } else {
            txt_destination_account.visibility = View.VISIBLE
            spinner_destination_account.visibility = View.VISIBLE
        }
    }

    override fun onSuccess(msg : String) {
        layout_progress.visibility = View.GONE
        if(msg.isNotEmpty())
            alert(msg,DialogInterface.OnClickListener { dialog, which -> finish() })
    }

    override fun initDependencies() {
        DaggerTransactionComponent.builder()
                .transactionModule( TransactionModule(this))
                .appComponent(component())
                .build()
                .inject(this)
    }
}