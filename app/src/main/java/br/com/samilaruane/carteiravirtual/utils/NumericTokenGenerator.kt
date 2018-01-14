package br.com.samilaruane.carteiravirtual.utils

import java.util.*

/**
 * Created by samila on 10/01/18.
 */
class NumericTokenGenerator : TokenGenerator {

    override fun generateToken(): String {
        val random = Random ()
        val randomicNumber = random.nextInt(9999 - 1000) + 1000
        val token = randomicNumber.toString()

        return token
    }
}