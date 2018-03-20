package br.com.samilaruane.carteiravirtual.ui.transaction

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.utils.constants.BaseConstants
import br.com.samilaruane.carteiravirtual.utils.Dialog
import br.com.samilaruane.carteiravirtual.utils.NeutralDialog
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity(), TransactionContract.View, AdapterView.OnItemSelectedListener {

    lateinit var mPresenter : TransactionContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        mPresenter = TransactionPresenter ()
        mPresenter.attachView(this)

        initViews()
    }

    override fun showError(error: String) {
        val dialog : Dialog = NeutralDialog()
        dialog.show(this, error)
    }

    override fun initViews() {

        progressBar.visibility = View.GONE

        spinner_transaction_type.adapter = ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                resources.getStringArray(R.array.transactiontypes))

        spinner_source_account.adapter = ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                resources.getStringArray(R.array.coins))

        spinner_destination_account.adapter = ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                resources.getStringArray(R.array.coins))

        spinner_transaction_type.onItemSelectedListener = this

        btn_save_transaction.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            if(edt_transaction_amount.text.toString().isNotEmpty()) {

                when (spinner_transaction_type.selectedItem.toString()){
                    BaseConstants.SELL -> {
                        mPresenter.saveTransaction(spinner_transaction_type.selectedItem.toString(),
                                spinner_source_account.selectedItem.toString(),
                                BaseConstants.BRL_ACCOUNT, edt_transaction_amount.text.toString().toDouble())
                        }
                    BaseConstants.BUY -> {
                        mPresenter.saveTransaction(spinner_transaction_type.selectedItem.toString(), BaseConstants.BRL_ACCOUNT,
                                spinner_source_account.selectedItem.toString(), edt_transaction_amount.text.toString().toDouble())
                        }
                    BaseConstants.TRADE ->{
                        mPresenter.saveTransaction(spinner_transaction_type.selectedItem.toString(),
                                spinner_source_account.selectedItem.toString(),
                                spinner_destination_account.selectedItem.toString(), edt_transaction_amount.text.toString().toDouble())
                    }

                }

            }else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (spinner_transaction_type.getItemAtPosition(position).equals(BaseConstants.SELL) ||
                spinner_transaction_type.getItemAtPosition(position).equals(BaseConstants.BUY)){
            txt_destination_account.visibility = View.GONE
            spinner_destination_account.visibility = View.GONE
        }else{
            txt_destination_account.visibility = View.VISIBLE
            spinner_destination_account.visibility = View.VISIBLE
        }
    }

    override fun onSuccess() {
        progressBar.visibility = View.GONE
        NeutralDialog().showDialogWithCallback(this, getString(R.string.success_on_transaction),
                DialogInterface.OnClickListener { dialog, which -> finish() })

    }

    override fun onError(msg: String) {
        progressBar.visibility = View.GONE
        NeutralDialog().show(this, msg)
    }
}
