package br.com.samilaruane.carteiravirtual.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.samilaruane.carteiravirtual.R
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.base.BaseActivity
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import br.com.samilaruane.carteiravirtual.ui.register.RegisterActivity
import br.com.samilaruane.carteiravirtual.utils.Dialog
import br.com.samilaruane.carteiravirtual.utils.NeutralDialog
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_progress.*

class LoginActivity : BaseActivity (), LoginContract.View {

    private val TAG = "_MainActivityTAG"


    lateinit var loginPresenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(SharedPreferencesHelper(this).isAuth()){
            navigateTo(MainActivity :: class.java)
        }

        loginPresenter = LoginPresenter()

        loginPresenter.attachView(this)

        initViews()

        val phoneNumberMask = SimpleMaskFormatter ("+NN (NN) NNNNN-NNNN")
        val phoneNumberWatcher = MaskTextWatcher(edt_login_phone_number,phoneNumberMask)
        edt_login_phone_number?.addTextChangedListener(phoneNumberWatcher)

        txt_user_register.setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
            }


            btn_login.setOnClickListener {
                if (edt_login_phone_number != null && edt_login_password != null &&
                        !edt_login_phone_number.equals("") &&
                        !edt_login_password.equals("")) {
                    loginPresenter.login(edt_login_phone_number.text.toString(), edt_login_password.text.toString())
                }else {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT)
                }
        }
    }

    override fun showError(error: String) {
        val dialog : Dialog = NeutralDialog()
        dialog.show(this, error)
    }


    override fun showProgress() {
        progress_text.text = "carregando"
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    fun initViews (){
        btn_login.setOnClickListener {  }
    }

}
