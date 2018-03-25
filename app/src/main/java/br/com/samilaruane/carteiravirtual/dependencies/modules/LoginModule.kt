package br.com.samilaruane.carteiravirtual.dependencies.modules

import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.ui.login.LoginContract
import br.com.samilaruane.carteiravirtual.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class LoginModule {

    val activity : LoginContract.View

    constructor(activity : LoginContract.View){
        this.activity = activity
    }

    @Provides
    fun provideLoginView () : LoginContract.View = activity

    @Provides
    fun provideLoginPresenter (userBusiness : UserBusiness, view : LoginContract.View) : LoginContract.Presenter = LoginPresenter(userBusiness, view)
}