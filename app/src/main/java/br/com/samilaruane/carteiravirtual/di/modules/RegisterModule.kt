package br.com.samilaruane.carteiravirtual.di.modules

import br.com.samilaruane.carteiravirtual.domain.account.AccountBoundary
import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.ui.register.RegisterContract
import br.com.samilaruane.carteiravirtual.ui.register.RegisterInteractor
import br.com.samilaruane.carteiravirtual.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class RegisterModule(private val activity: RegisterContract.View) {

    @Provides
    fun provideView(): RegisterContract.View = activity

    @Provides
    fun providePresenter(view : RegisterContract.View, interactor : RegisterContract.Interactor): RegisterContract.Presenter =
            RegisterPresenter(view, interactor)

    @Provides
    fun provideInteractor (userBusiness : UserBoundary, authBusiness: AuthBoundary, accountBusiness: AccountBoundary) : RegisterContract.Interactor =
            RegisterInteractor(userBusiness, authBusiness, accountBusiness)


}