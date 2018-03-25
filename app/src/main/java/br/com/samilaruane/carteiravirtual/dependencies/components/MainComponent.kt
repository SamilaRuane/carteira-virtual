package br.com.samilaruane.carteiravirtual.dependencies.components

import br.com.samilaruane.carteiravirtual.dependencies.PerActivity
import br.com.samilaruane.carteiravirtual.dependencies.modules.MainModule
import br.com.samilaruane.carteiravirtual.ui.main.MainActivity
import dagger.Component

/**
 * Created by samila on 24/03/18.
 */
@PerActivity
@Component( modules = arrayOf(MainModule:: class), dependencies = arrayOf(AppComponent :: class))
interface MainComponent {
    fun inject (activity : MainActivity)
}