package br.com.samilaruane.carteiravirtual

import android.app.Application
import br.com.samilaruane.carteiravirtual.di.components.AppComponent
import br.com.samilaruane.carteiravirtual.di.components.DaggerAppComponent
import br.com.samilaruane.carteiravirtual.di.modules.AppModule

/**
 * Created by samila on 21/03/18.
 */
class App : Application(){

    val appComponent : AppComponent by lazy {
            DaggerAppComponent.builder().appModule(AppModule(this)).build()
        }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun getComponent () : AppComponent = appComponent
}