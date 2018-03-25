package br.com.samilaruane.carteiravirtual.dependencies.modules

import br.com.samilaruane.carteiravirtual.domain.UserBusiness
import br.com.samilaruane.carteiravirtual.repository.SharedPreferencesHelper
import br.com.samilaruane.carteiravirtual.ui.register.RegisterContract
import br.com.samilaruane.carteiravirtual.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by samila on 25/03/18.
 */
@Module
class RegisterModule {

    private val activity: RegisterContract.View

    constructor(activity: RegisterContract.View) {
        this.activity = activity
    }

    @Provides
    fun provideRegisterView(): RegisterContract.View = activity

    @Provides
    fun provideRegisterPresenter(view : RegisterContract.View, userBusiness : UserBusiness, preferences : SharedPreferencesHelper): RegisterContract.Presenter =
            RegisterPresenter(view, userBusiness, preferences)

}