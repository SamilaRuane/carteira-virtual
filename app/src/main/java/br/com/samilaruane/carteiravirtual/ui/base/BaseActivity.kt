package br.com.samilaruane.carteiravirtual.ui.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.samilaruane.carteiravirtual.R

/**
 * Created by samila on 14/02/18.
 */
open class BaseActivity : AppCompatActivity() {

    fun initFragment (fragment : Fragment){
        supportFragmentManager.
                beginTransaction().
                replace(R.id.register_container, fragment).commit()

    }
}