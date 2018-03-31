package br.com.samilaruane.carteiravirtual.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by samila on 20/12/17.
 */
class TabsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

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