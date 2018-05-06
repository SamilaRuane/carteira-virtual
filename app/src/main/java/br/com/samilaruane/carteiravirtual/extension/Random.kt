package br.com.samilaruane.carteiravirtual.extension

import java.util.*

/**
 * Created by samila on 26/03/18.
 */
fun Random.generateToken () : String{
    val randomicNumber = this.nextInt(9999 - 1000) + 1000

    return randomicNumber.toString()
}