package br.com.samilaruane.carteiravirtual.di.components

import br.com.samilaruane.carteiravirtual.di.PerActivity
import br.com.samilaruane.carteiravirtual.di.modules.MainModule
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