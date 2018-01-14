package br.com.samilaruane.carteiravirtual.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.Transaction
import kotlinx.android.synthetic.main.transaction_item.view.*

/**
 * Created by samila on 21/12/17.
 */
class TransactionListAdapter (private val transactions : List<Transaction>, private val ctx : Context): RecyclerView.Adapter<TransactionListAdapter.ViewHolder> () {

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val transaction = transactions[position]
        holder?.let{
            it.bindView(transaction)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.transaction_item, parent, false)
        return ViewHolder (view)
    }


    class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){

        fun bindView (transaction : Transaction) {
            //TODO associar um simbolo de cada moeda
            val symbol = itemView.img_coin_symbol
            val info = itemView.txt_transaction

           info.text = transaction.toString()

        }
    }
}