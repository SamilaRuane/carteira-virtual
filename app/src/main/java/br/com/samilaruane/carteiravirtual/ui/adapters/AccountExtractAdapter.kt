package br.com.samilaruane.carteiravirtual.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.Transaction
import br.com.samilaruane.carteiravirtual.extension.inflate
import kotlinx.android.synthetic.main.transaction_item.view.*

/**
 * Created by samila on 22/12/17.
 */
class AccountExtractAdapter(private val transactions : List<Transaction> ) : RecyclerView.Adapter<AccountExtractAdapter.ViewHolder> (){

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindView(transactions[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view : View = parent?.inflate(R.layout.transaction_item)!!
        return AccountExtractAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView){
        val accountBalance : TextView? = itemView.txt_transaction

        fun bindView (item : Transaction){
            accountBalance?.text = item.toString()
        }
    }
}