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
class UserInfoFragment : Fragment () {

    lateinit var mListener : OnRegisterFinishedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mListener = context as OnRegisterFinishedListener
        }catch (e : ClassCastException){
            throw ClassCastException ("This class must implement OnPhoneNumberTypedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_user_info, container, false)
        val name = view?.findViewById<EditText>(R.id.edt_user_name)
        val email = view?.findViewById<EditText>(R.id.edt_user_email)
        val password = view?.findViewById<EditText>(R.id.edt_user_password)
        val passwordToConfirm = view?.findViewById<EditText>(R.id.edt_user_password_confirmation)
        val confirmButton = view?.findViewById<Button>(R.id.btn_create_user)

        confirmButton?.setOnClickListener {
            mListener.onRegisterFinished(name?.text.toString(),
                    email?.text.toString(),
                    password?.text.toString(),
                    passwordToConfirm?.text.toString())}

        return view
    }

    interface OnRegisterFinishedListener {
        fun onRegisterFinished(name : String, email : String, password : String, passwordConfirmation: String)
    }
}