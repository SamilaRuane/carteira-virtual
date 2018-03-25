package br.com.samilaruane.carteiravirtual.ui.register

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.dependencies.components.DaggerRegisterComponent
import br.com.samilaruane.carteiravirtual.dependencies.modules.RegisterModule
import br.com.samilaruane.carteiravirtual.extension.alert
import br.com.samilaruane.carteiravirtual.extension.component
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import br.com.samilaruane.carteiravirtual.utils.Dialog
import br.com.samilaruane.carteiravirtual.utils.NeutralDialog
import br.com.samilaruane.carteiravirtual.utils.PermissionManager
import javax.inject.Inject

class RegisterActivity : BaseActivity (), RegisterContract.View, SendCodeFragment.OnPhoneNumberTypedListener,
ConfirmCodeFragment.OnCodeConfirmedListener,
UserInfoFragment.OnRegisterFinishedListener{

    @Inject
    lateinit var registerPresenter : RegisterContract.Presenter
    lateinit var phoneNumber : String

    private val REQUEST_DANGEROUS_PERMISSIONS_CODE = 1
    private val permissions : Array<String> = arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        PermissionManager.requestPermissions (this, permissions, REQUEST_DANGEROUS_PERMISSIONS_CODE )

        initDependencies()
        initFragment(SendCodeFragment())

    }

    override fun showError(error: String) {
        val dialog : Dialog = NeutralDialog()
        alert( error, null )
    }


    override fun onCodeConfirmed(code: String) {

        if (code.equals(registerPresenter.getToken())){
            initFragment(UserInfoFragment())
        }else {
            val dialog = NeutralDialog()
            alert("Token Inválido", null )
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
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == REQUEST_DANGEROUS_PERMISSIONS_CODE){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                val listener = DialogInterface.OnClickListener({ dialog, which ->
                    dialog.dismiss()
                    finish()
                })
                alert(getString(R.string.permissions_alert),listener)
            }
        }
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
}
