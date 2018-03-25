package br.com.samilaruane.carteiravirtual.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by samila on 20/03/18.
 */

fun Calendar.addDays (days : Int) = this.add(Calendar.DATE, days)

fun Calendar.isSaturday () : Boolean = this.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY

fun Calendar.isSunday () : Boolean = this.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY

fun Calendar.isWorkingDay () : Boolean = !this.isSaturday() && !this.isSunday()

fun Calendar.formatter (pattern : String) : String{
    val sdf = SimpleDateFormat (pattern)
    return sdf?.format(this.time)
}
