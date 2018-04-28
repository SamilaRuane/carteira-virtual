package br.com.samilaruane.carteiravirtual.di.modules

import br.com.samilaruane.carteiravirtual.domain.auth.AuthBoundary
import br.com.samilaruane.carteiravirtual.domain.user.UserBoundary
import br.com.samilaruane.carteiravirtual.ui.recoverypassw.RecoveryPasswordContract
import br.com.samilaruane.carteiravirtual.ui.recoverypassw.RecoveryPasswordInteractor
import br.com.samilaruane.carteiravirtual.ui.recoverypassw.RecoveryPasswordPresenter
import dagger.Module
import dagger.Provides

@Module
class RecoveryPasswordModule {

    private val mView: RecoveryPasswordContract.View

    constructor(mView: RecoveryPasswordContract.View) {
        this.mView = mView
    }


    @Provides
    fun provideView(): RecoveryPasswordContract.View = mView

    @Provides
    fun providePresenter(view: RecoveryPasswordContract.View, interactor: RecoveryPasswordContract.Interactor):
            RecoveryPasswordContract.Presenter = RecoveryPasswordPresenter(view, interactor)

    @Provides
    fun provideInteractor(authBusiness: AuthBoundary, userBusiness: UserBoundary):
            RecoveryPasswordContract.Interactor = RecoveryPasswordInteractor(authBusiness, userBusiness)

}