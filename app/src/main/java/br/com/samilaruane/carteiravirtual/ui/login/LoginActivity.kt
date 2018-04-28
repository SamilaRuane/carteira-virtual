package br.com.samilaruane.carteiravirtual.ui.login

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.data.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.di.components.DaggerLoginComponent
import br.com.samilaruane.carteiravirtual.di.modules.LoginModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.ui.recoverypassw.RecoveryPasswordActivity
import br.com.samilaruane.carteiravirtual.ui.register.RegisterActivity
import br.com.samilaruane.carteiravirtual.utils.PermissionManager
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_login.*
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
    override fun onError(error: String) {
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
           navigateTo(RecoveryPasswordActivity :: class.java)
        }

    }
}
