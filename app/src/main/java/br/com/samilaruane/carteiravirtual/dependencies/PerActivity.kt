package br.com.samilaruane.carteiravirtual.dependencies

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by samila on 25/03/18.
 */

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
@Scope
annotation class PerActivity