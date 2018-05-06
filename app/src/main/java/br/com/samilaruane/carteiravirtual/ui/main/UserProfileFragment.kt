package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.fragment_user_profile.*


/**
 * Created by samila on 20/12/17.
 */
class UserProfileFragment : Fragment(), EventResponseListener<User> {

    private var user : User = User(0, "", "", "", "")
    private lateinit var mListener : UserProfileListener

    /* Fragment Lifecycle */

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_user_name.text = user.name
        txt_user_phone.text = user.phone
        txt_user_email.text = user.email

        val phoneNumberMask = SimpleMaskFormatter("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(user_profile_edit_phone_number, phoneNumberMask)
        user_profile_edit_phone_number?.addTextChangedListener(phoneNumberWatcher)

        profile_exit_button.setOnClickListener {
            SharedPreferencesHelper(activity).setIsAuth(false)
            startActivity(Intent(activity, LoginActivity :: class.java))
        }
        profile_edit_button.setOnClickListener {
            user_profile_info_card.visibility = View.GONE
            user_profile_edit_card.visibility = View.VISIBLE
            phone_linear.visibility = View.GONE
        }
        user_profile_save_button.setOnClickListener {
           if (user_profile_edit_user_name.text.toString().isNotEmpty())
            user.name = user_profile_edit_user_name.text.toString()

            if (user_profile_edit_email.text.toString().isNotEmpty())
            user.email = user_profile_edit_email.text.toString()

            if (user_profile_edit_phone_number.text.toString().isNotEmpty())
            user.phone = user_profile_edit_phone_number.text.toString()

            if (user_profile_edit_password.text.toString().isNotEmpty())
            user.password = user_profile_edit_password.text.toString()

            user_profile_edit_card.visibility = View.GONE

            txt_user_name.text = user.name
            txt_user_phone.text = user.phone
            txt_user_email.text = user.email

            user_profile_info_card.visibility = View.VISIBLE
            mListener.onSaveUserClicked(user)
        }
        user_profile_back_button.setOnClickListener {
            user_profile_edit_card.visibility = View.GONE
            user_profile_info_card.visibility = View.VISIBLE
        }

    }

    /* Event Response Listener */
    override fun onSuccess(obj: User) {
        user = obj
    }

    override fun onError(errorMessage: String) {
        activity.alert(errorMessage, null)
    }

    fun setOnClickListener (listener : UserProfileListener){
        mListener = listener
    }

    interface UserProfileListener {
        fun onSaveUserClicked (user : User)
    }

}