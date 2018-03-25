package br.com.samilaruane.carteiravirtual.extension

/**
 * Created by samila on 20/03/18.
 */

fun String.cleanPhoneMask () : String{
    this.replace("+","")
    this.replace("-", "")
    this.replace("(", "")
    this.replace(")", "")

    return this
}