package br.com.samilaruane.carteiravirtual.extension

import java.math.BigDecimal

/**
 * Created by samila on 25/03/18.
 */
fun Double.roundTo(digits : Int) =
    BigDecimal(this).setScale(digits, BigDecimal.ROUND_HALF_UP).toDouble()
