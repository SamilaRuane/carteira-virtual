package br.com.samilaruane.carteiravirtual.ui.recoverypassw

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.di.components.DaggerRecoveryPasswordComponent
import br.com.samilaruane.carteiravirtual.di.modules.RecoveryPasswordModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_recovery_password.*
import javax.inject.Inject

class RecoveryPasswordActivity : AppCompatActivity(), RecoveryPasswordContract.View {

    @Inject
    lateinit var mPresenter: RecoveryPasswordContract.Presenter

    /* Activity Lifecycle */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_password)
        initDependencies()

        supportActionBar?.title = getString(R.string.recovery_password_screen)

        recoveryPassword()
    }


    /* RecoveryPasswordContract */
    override fun onSuccess() {
        alert(getString(R.string.recovery_password_success_message),
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    this.finish()
                })
    }

    override fun onError(error: String) {
        alert(error, null)
    }

    override fun initDependencies() = DaggerRecoveryPasswordComponent.builder()
            .recoveryPasswordModule(RecoveryPasswordModule(this))
            .appComponent(component())
            .build()
            .inject(this)

    private fun recoveryPassword() {

        val phoneNumberMask = SimpleMaskFormatter("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(recovery_password_phonenumber, phoneNumberMask)
        recovery_password_phonenumber?.addTextChangedListener(phoneNumberWatcher)
        recovery_password_description.text = getString(R.string.recovery_password_ask_phone_number)

        recovery_password_btn_go_to_code.setOnClickListener {
            if (mPresenter.sendRecoveryCode(recovery_password_phonenumber.text.toString())) {
                recovery_password_phonenumber.visibility = View.GONE
                recovery_password_code.visibility = View.VISIBLE
                recovery_password_btn_go_to_code.visibility = View.GONE
                recovery_password_btn_next.visibility = View.VISIBLE
                recovery_password_description.text = getString(R.string.recovery_password_description)
            }else{
                alert(getString(R.string.recovery_password_send_code_error), null)
            }
        }

        recovery_password_btn_next.setOnClickListener {
            if (mPresenter.checkRecoveryCode(recovery_password_code.text.toString())) {
                recovery_password_code.visibility = View.GONE
                recovery_password_btn_next.visibility = View.GONE
                recovery_password_btn_finish.visibility = View.VISIBLE
                recovery_password_new_password.visibility = View.VISIBLE
                recovery_password_description.text = getString(R.string.recovery_password_ask_new_pass)
            }else{
                alert(getString(R.string.recovery_password_incorrect_code), null)
            }
        }

        recovery_password_btn_finish.setOnClickListener {
            if (mPresenter.changePassword(recovery_password_new_password.text.toString())) {
                onSuccess()
            } else onError(getString(R.string.recovery_password_error_on_change_pass))

        }

    }
}
