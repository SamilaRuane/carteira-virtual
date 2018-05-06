package br.com.samilaruane.carteiravirtual.ui.register

import android.content.DialogInterface
import android.os.Bundle
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.di.components.DaggerRegisterComponent
import br.com.samilaruane.carteiravirtual.di.modules.RegisterModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterContract.View, PhoneNumberFragment.OnPhoneNumberTypedListener,
        CodeFragment.OnCodeConfirmedListener,
        UserInfoFragment.OnRegisterFinishedListener {

    @Inject
    lateinit var registerPresenter: RegisterContract.Presenter
    lateinit var phoneNumber: String


    /* Activity Lifecycle */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initDependencies()
        initFragment(PhoneNumberFragment())

    }

    /* Register Contract */
    override fun onError(error: String) {
        alert(error, null)
    }

    override fun onSuccess() {
        alert(getString(R.string.success_on_registration), DialogInterface.OnClickListener { dialog, which ->
            dialog?.dismiss()
            navigateTo(LoginActivity::class.java)
            finish()
        })
    }

    override fun initDependencies() {
        DaggerRegisterComponent.builder()
                .registerModule(RegisterModule(this))
                .appComponent(component())
                .build()
                .inject(this)
    }

    /* Fragments Listeners */
    override fun onCodeConfirmed(code: String) {

        if (registerPresenter.verifyToken(code))
            initFragment(UserInfoFragment())
        else
            alert("Token Inválido", null)


    }

    override fun onPhoneNumberTyped(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        registerPresenter.sendMessage(phoneNumber)
        alert(getString(R.string.send_code_message), DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            initFragment(CodeFragment())
        })
    }

    override fun onRegisterFinished(name: String, email: String, password: String, passwordConfirmation: String) {
        registerPresenter.create(name, email, phoneNumber, password, passwordConfirmation)
    }
}
