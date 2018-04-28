package br.com.samilaruane.carteiravirtual.di.components

import br.com.samilaruane.carteiravirtual.di.PerActivity
import br.com.samilaruane.carteiravirtual.di.modules.RegisterModule
import br.com.samilaruane.carteiravirtual.ui.register.RegisterActivity
import dagger.Component

/**
 * Created by samila on 25/03/18.
 */
@PerActivity
@Component (modules = arrayOf(RegisterModule :: class ), dependencies = arrayOf(AppComponent :: class))
interface RegisterComponent {
    fun inject (activity : RegisterActivity)
}