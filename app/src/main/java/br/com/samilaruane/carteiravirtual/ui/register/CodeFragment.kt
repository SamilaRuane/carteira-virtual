package br.com.samilaruane.carteiravirtual.ui.register


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.extension.inflate
import kotlinx.android.synthetic.main.fragment_type_code.*

/**
 * Created by samila on 02/01/18.
 */
class CodeFragment : Fragment(){

    lateinit var mListener : OnCodeConfirmedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mListener = context as OnCodeConfirmedListener
        }catch (e : ClassCastException){
            throw ClassCastException ("This class must implement OnPhoneNumberTypedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_type_code)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_type_code_send?.setOnClickListener {
            mListener.onCodeConfirmed(edt_type_phone_number?.text.toString()) }

    }

    interface OnCodeConfirmedListener{
        fun onCodeConfirmed (code : String)
    }
}