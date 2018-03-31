package br.com.samilaruane.carteiravirtual.dependencies.modules

import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.login.LoginContract
import br.com.samilaruane.carteiravirtual.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class LoginModule(val activity: LoginContract.View) {

    @Provides
    fun provideLoginView () : LoginContract.View = activity

    @Provides
    fun provideLoginPresenter (userBusiness : UserBusiness, view : LoginContract.View, preferences : SharedPreferencesHelper) : LoginContract.Presenter = LoginPresenter(userBusiness, view, preferences)
}