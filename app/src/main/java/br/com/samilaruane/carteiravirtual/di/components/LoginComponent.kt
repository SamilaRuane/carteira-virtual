package br.com.samilaruane.carteiravirtual.di.components

import br.com.samilaruane.carteiravirtual.di.PerActivity
import br.com.samilaruane.carteiravirtual.di.modules.LoginModule
import br.com.samilaruane.carteiravirtual.ui.login.LoginActivity
import dagger.Component

/**
 * Created by samila on 25/03/18.
 */
@PerActivity
@Component (modules = arrayOf(LoginModule :: class), dependencies = arrayOf(AppComponent :: class))
interface LoginComponent {
    fun inject (activity : LoginActivity)
}