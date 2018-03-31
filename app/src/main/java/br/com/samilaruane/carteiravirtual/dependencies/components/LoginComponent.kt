package br.com.samilaruane.carteiravirtual.dependencies.components

import br.com.samilaruane.carteiravirtual.dependencies.PerActivity
import br.com.samilaruane.carteiravirtual.dependencies.modules.LoginModule
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