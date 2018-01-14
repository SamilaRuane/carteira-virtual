package br.com.samilaruane.carteiravirtual.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by samila on 20/12/17.
 */
class TabsPagerAdapter : FragmentPagerAdapter {

    constructor(fm: FragmentManager?) : super(fm)

    var mFragment = ArrayList<Fragment> ()

    fun addFragment (fragment : Fragment){
        mFragment.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }

    override fun getCount(): Int {
        return mFragment.size
    }
}