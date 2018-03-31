package br.com.samilaruane.carteiravirtual.ui.login

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.dependencies.components.DaggerLoginComponent
import br.com.samilaruane.carteiravirtual.dependencies.modules.LoginModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.ui.register.RegisterActivity
import br.com.samilaruane.carteiravirtual.utils.PermissionManager
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_recovery_password.view.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var loginPresenter: LoginContract.Presenter

    private val REQUEST_DANGEROUS_PERMISSIONS_CODE = 1
    private val permissions : Array<String> = arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE)


    /* Activity Lifecycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (SharedPreferencesHelper(this).isAuth()) {
            navigateTo(MainActivity::class.java)
        }

        PermissionManager.requestPermissions (this, permissions, REQUEST_DANGEROUS_PERMISSIONS_CODE )


        val phoneNumberMask = SimpleMaskFormatter("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(edt_login_phone_number, phoneNumberMask)
        edt_login_phone_number?.addTextChangedListener(phoneNumberWatcher)

        initDependencies()
        initViews()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == REQUEST_DANGEROUS_PERMISSIONS_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                val listener = DialogInterface.OnClickListener({ dialog, which ->
                    dialog.dismiss()
                    finish()
                })
                alert(getString(R.string.permissions_alert),listener)
            }
        }
    }

    /* MainContract.View */
    override fun showError(error: String) {
        alert(error, null)
    }

    override fun showProgress() {
        progress_text.text = "carregando"
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun initDependencies() {
        DaggerLoginComponent.builder()
                .loginModule(LoginModule(this))
                .appComponent(component())
                .build()
                .inject(this)
    }

    /* private methods */
    private fun initViews() {
        btn_login.setOnClickListener {
            if (edt_login_phone_number != null && edt_login_password != null &&
                    !edt_login_phone_number.text.equals("") &&
                    !edt_login_password.text.equals("")) {
                loginPresenter.login(edt_login_phone_number.text.toString(), edt_login_password.text.toString())
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT)
            }
        }

        txt_user_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        txt_recovery_password.setOnClickListener {
           recoveryPassword()
        }

    }

    private fun recoveryPassword (){
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_recovery_password, null, false)
        val dialog = AlertDialog.Builder(this).setView(view).setCancelable(true).show()

        val phoneNumberMask = SimpleMaskFormatter("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(view.recovery_password_phonenumber, phoneNumberMask)
        view.recovery_password_phonenumber?.addTextChangedListener(phoneNumberWatcher)
        view.recovery_password_description.text = getString(R.string.recovery_password_ask_phone_number)

        view.recovery_password_btn_go_to_code.setOnClickListener {
            if(loginPresenter.sendRecoveryCode(view.recovery_password_phonenumber.text.toString(), getString(R.string.recovery_password_message))){
                view.recovery_password_phonenumber.visibility = View.GONE
                view.recovery_password_code.visibility = View.VISIBLE
                view.recovery_password_btn_go_to_code.visibility = View.GONE
                view.recovery_password_btn_next.visibility = View.VISIBLE
                view.recovery_password_description.text = getString(R.string.recovery_password_description)
            }
        }

        view.recovery_password_btn_next.setOnClickListener {
            if(loginPresenter.ckeckRecoveryCode(view.recovery_password_code.text.toString())){
                view.recovery_password_code.visibility = View.GONE
                view.recovery_password_btn_next.visibility = View.GONE
                view.recovery_password_btn_finish.visibility = View.VISIBLE
                view.recovery_password_new_password.visibility = View.VISIBLE
                view.recovery_password_description.text = getString(R.string.recovery_password_ask_new_pass)
            }
        }

        view.recovery_password_btn_finish.setOnClickListener {
            if(loginPresenter.changePassword(view.recovery_password_new_password.text.toString())){
                alert(getString(R.string.recovery_password_success_message), null)
            }else showError(getString(R.string.recovery_password_error_on_change_pass))

                dialog.dismiss()
        }

    }
}
