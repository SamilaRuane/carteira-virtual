package br.com.samilaruane.carteiravirtual.utils

import java.util.*

/**
 * Created by samila on 18/03/18.
 */
class DataUtils {

    companion object {
        fun isWorkingDay (date : Calendar) : Boolean =
                !isSunday(date) || !isSaturday(date)

        fun isSunday (date : Calendar) : Boolean =
                date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY

        fun isSaturday (date : Calendar) : Boolean =
                date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY

        fun addDays (data : Calendar, days : Int)  =
                data.add(Calendar.DATE, days)
    }
}