package br.com.samilaruane.carteiravirtual.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.Account
import kotlinx.android.synthetic.main.account_item.view.*

/**
 * Created by samila on 22/12/17.
 */
class AccountListAdapter (private val ctx : Context, private val accountList : List<Account> ) : RecyclerView.Adapter<AccountListAdapter.ViewHolder> (){

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let{
            it.bindView(accountList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.account_item, parent, false)
        return AccountListAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView){
        val coinSymbol = itemView.img_acc_list_coin_symbol
        val coinInitials : TextView? = itemView.txt_acc_list_coin_initials
        val accountBalance : TextView? = itemView.txt_acc_list_acc_balance

        fun bindView (item : Account){
            accountBalance?.text = item.getAccountBalance().toString()
            coinInitials?.text = item.getCoin().getCoinInitials()
        }
    }
}