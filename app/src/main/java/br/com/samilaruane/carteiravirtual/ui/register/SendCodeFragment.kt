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
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import java.util.logging.SimpleFormatter

/**
 * Created by samila on 02/01/18.
 */
class SendCodeFragment : Fragment () {

    lateinit var mListener : OnPhoneNumberTypedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            mListener = context as OnPhoneNumberTypedListener
        }catch (e : ClassCastException){
            throw ClassCastException("This class must implement OnPhoneNumberTypedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_type_phone_number, container, false)
        val sendButton = view?.findViewById<Button>(R.id.btn_send_to_phone_number)
        val phoneNumber = view?.findViewById<EditText>(R.id.edt_type_code)

        val phoneNumberMask = SimpleMaskFormatter ("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(phoneNumber,phoneNumberMask)
        phoneNumber?.addTextChangedListener(phoneNumberWatcher)

        sendButton?.setOnClickListener {
            mListener.onPhoneNumberTyped(phoneNumber?.text.toString())
        }
        return view
    }

    interface OnPhoneNumberTypedListener {
        fun onPhoneNumberTyped (phoneNumber : String)
    }
}