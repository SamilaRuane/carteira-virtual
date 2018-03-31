package br.com.samilaruane.carteiravirtual.ui.register

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.extension.inflate
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.fragment_type_phone_number.*

/**
 * Created by samila on 02/01/18.
 */
class PhoneNumberFragment : Fragment () {

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
        val view = container?.inflate(R.layout.fragment_type_phone_number)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNumberMask = SimpleMaskFormatter ("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(edt_type_phone_number,phoneNumberMask)

        edt_type_phone_number?.addTextChangedListener(phoneNumberWatcher)

        btn_send_to_phone_number?.setOnClickListener {
            mListener.onPhoneNumberTyped(edt_type_phone_number?.text.toString())
        }
    }

    interface OnPhoneNumberTypedListener {
        fun onPhoneNumberTyped (phoneNumber : String)
    }
}