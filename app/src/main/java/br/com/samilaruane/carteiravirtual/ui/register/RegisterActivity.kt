package br.com.samilaruane.carteiravirtual.ui.register

import android.content.DialogInterface
import android.os.Bundle
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.dependencies.components.DaggerRegisterComponent
import br.com.samilaruane.carteiravirtual.dependencies.modules.RegisterModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import javax.inject.Inject

class RegisterActivity : BaseActivity (), RegisterContract.View, PhoneNumberFragment.OnPhoneNumberTypedListener,
CodeFragment.OnCodeConfirmedListener,
UserInfoFragment.OnRegisterFinishedListener{

    @Inject
    lateinit var registerPresenter : RegisterContract.Presenter
    lateinit var phoneNumber : String


    /* Activity Lifecycle */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initDependencies()
        initFragment(PhoneNumberFragment())

    }

   /* Register Contract */
    override fun showError(error: String) {
        alert( error, null )
    }

    override fun onSuccess() {
        alert(getString(R.string.success_on_registration), object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
                navigateTo(LoginActivity::class.java)
                finish()
            }
        })
    }

    override fun initDependencies() {
        DaggerRegisterComponent.builder()
                .registerModule(RegisterModule (this))
                .appComponent(component())
                .build()
                .inject(this)
    }

    /* Fragments Listeners */
    override fun onCodeConfirmed(code: String) {

        if (code.equals(registerPresenter.getToken())){
            initFragment(UserInfoFragment())
        }else {
            alert("Token Inválido", null )
        }

    }

    override fun onPhoneNumberTyped(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        val token = registerPresenter.generateToken()
        registerPresenter.sendMessage(phoneNumber, token)
        registerPresenter.saveTokenOnPreference(phoneNumber, token)
        initFragment(CodeFragment())
    }

    override fun onRegisterFinished(name: String, email: String, password: String, passwordConfirmation: String) {
        registerPresenter.create(name, email, phoneNumber, password, passwordConfirmation)
    }
}
