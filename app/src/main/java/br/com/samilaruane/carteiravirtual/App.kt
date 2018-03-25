package br.com.samilaruane.carteiravirtual

import android.app.Application
import br.com.samilaruane.carteiravirtual.dependencies.components.AppComponent
import br.com.samilaruane.carteiravirtual.dependencies.components.DaggerAppComponent
import br.com.samilaruane.carteiravirtual.dependencies.modules.AppModule
import br.com.samilaruane.carteiravirtual.repository.db.AccountRepository
import javax.inject.Inject

/**
 * Created by samila on 21/03/18.
 */
class App : Application(){

    val appComponent : AppComponent by lazy {
            DaggerAppComponent.builder().appModule(AppModule(this)).build()
        }

    @Inject
    lateinit var accountRepository : AccountRepository


    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun getComponent () : AppComponent = appComponent
}