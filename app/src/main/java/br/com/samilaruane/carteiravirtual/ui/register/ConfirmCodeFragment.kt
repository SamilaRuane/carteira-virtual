package br.com.samilaruane.carteiravirtual.ui.register


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import br.com.samilaruane.carteiravirtual.R

/**
 * Created by samila on 02/01/18.
 */
class ConfirmCodeFragment : Fragment(){

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
        val view = inflater?.inflate(R.layout.fragment_type_code, container, false)
        val sendButton = view?.findViewById<Button>(R.id.btn_type_code_send)
        val code = view?.findViewById<EditText>(R.id.edt_type_code)

        sendButton?.setOnClickListener { mListener.onCodeConfirmed(code?.text.toString()) }

        return view
    }

    interface OnCodeConfirmedListener{
        fun onCodeConfirmed (code : String)
    }
}