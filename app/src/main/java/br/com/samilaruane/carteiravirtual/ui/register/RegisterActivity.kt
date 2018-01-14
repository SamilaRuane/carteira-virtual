package br.com.samilaruane.carteiravirtual.ui.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import br.com.samilaruane.carteiravirtual.utils.Dialog
import br.com.samilaruane.carteiravirtual.utils.ErrorDialog

class RegisterActivity : AppCompatActivity(), RegisterContract.View, SendCodeFragment.OnPhoneNumberTypedListener,
ConfirmCodeFragment.OnCodeConfirmedListener,
UserInfoFragment.OnRegisterFinishedListener{

    lateinit var registerPresenter : RegisterContract.Presenter
    lateinit var phoneNumber : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerPresenter = RegisterPresenter ()
        registerPresenter.attachView(this)
        initFragment(SendCodeFragment())
    }

    override fun showError(error: String) {
        val dialog : Dialog = ErrorDialog()
        dialog.show(this, error)
    }

    fun initFragment (fragment : Fragment){
        supportFragmentManager.
                beginTransaction().
                replace(R.id.register_container, fragment).commit()

    }

    override fun onCodeConfirmed(code: String) {

        if (code.equals(registerPresenter.getToken())){
            initFragment(UserInfoFragment())
        }else {
            val dialog = ErrorDialog ()
            dialog.show(this, "Token Inválido")
        }

    }

    override fun onPhoneNumberTyped(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        val token = registerPresenter.generateToken()
        registerPresenter.sendMessage(phoneNumber, token)
        registerPresenter.saveTokenOnPreference(phoneNumber, token)
        initFragment(ConfirmCodeFragment())
    }

    override fun onRegisterFinished(name: String, email: String, password: String, passwordConfirmation: String) {
        registerPresenter.create(name, email, phoneNumber, password, passwordConfirmation)
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
