package br.com.samilaruane.carteiravirtual.di.components

import br.com.samilaruane.carteiravirtual.di.PerActivity
import br.com.samilaruane.carteiravirtual.di.modules.RecoveryPasswordModule
import br.com.samilaruane.carteiravirtual.ui.recoverypassw.RecoveryPasswordActivity
import dagger.Component

@PerActivity
@Component (modules = arrayOf(RecoveryPasswordModule :: class), dependencies = arrayOf(AppComponent :: class))
interface RecoveryPasswordComponent {
    fun inject (activity : RecoveryPasswordActivity)
}