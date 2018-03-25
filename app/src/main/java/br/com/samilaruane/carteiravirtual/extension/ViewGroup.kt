package br.com.samilaruane.carteiravirtual.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by samila on 20/03/18.
 */

fun ViewGroup.inflate (layoutRes : Int) : View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}