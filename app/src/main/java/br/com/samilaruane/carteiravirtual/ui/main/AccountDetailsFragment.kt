package br.com.samilaruane.carteiravirtual.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R

/**
 * Created by samila on 20/12/17.
 */
class AccountDetailsFragment : Fragment {

    constructor() : super()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_account_details, container, false)
        return view
    }
}