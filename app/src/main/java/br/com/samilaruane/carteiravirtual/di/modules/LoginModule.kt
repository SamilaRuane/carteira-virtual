package br.com.samilaruane.carteiravirtual.di.modules

import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.ui.login.LoginContract
import br.com.samilaruane.carteiravirtual.ui.login.LoginInteractor
import br.com.samilaruane.carteiravirtual.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class LoginModule(val activity: LoginContract.View) {

    @Provides
    fun provideLoginView(): LoginContract.View = activity

    @Provides
    fun provideLoginPresenter(interactor: LoginContract.Interactor, view: LoginContract.View):
            LoginContract.Presenter = LoginPresenter(interactor, view)

    @Provides
    fun provideLoginInteractor(authBusiness: AuthBoundary): LoginContract.Interactor =
            LoginInteractor(authBusiness)

}