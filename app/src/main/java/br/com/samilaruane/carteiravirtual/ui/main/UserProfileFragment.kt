package br.com.samilaruane.carteiravirtual.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.domain.entities.User
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import br.com.samilaruane.carteiravirtual.utils.EventResponseListener
import kotlinx.android.synthetic.main.fragment_user_profile.*


/**
 * Created by samila on 20/12/17.
 */
class UserProfileFragment : Fragment, EventResponseListener<User> {

    private var user : User = User (0, "", "", "", "")
    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_user_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_user_name.text = user.name
        txt_user_phone.text = user.phone
        txt_user_email.text = user.email

        profile_exit_button.setOnClickListener {
            SharedPreferencesHelper(activity).setIsAuth(false)
            startActivity(Intent(activity, LoginActivity :: class.java))
        }
        profile_edit_button.setOnClickListener {
            //TODO implementar edição
        }

    }

    override fun onSuccess(obj: User) {
        user = obj
    }

    override fun onError(errorMessage: String) {
        activity.alert(errorMessage, null)
    }
}