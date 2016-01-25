package com.giantcroissant.blender.app

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by apprentice on 1/25/16.
 */
class ConnectionSectionPagerAdapter(fm: FragmentManager, val c: Context?) : FragmentStatePagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment {
        val defaultFragment = EmptyFragment.newInstance()
        val fragment = when(position) {
            0 -> ConnectionConnectFragment.newInstance()
            1 -> ConnectionControlFragment.newInstance()
            else -> defaultFragment
        }

        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        val defaultTitle = ""
        val title = when(position) {
            0 -> c?.resources?.getString(R.string.tab_connection_connect) ?: defaultTitle
            1 -> c?.resources?.getString(R.string.tab_connection_control) ?: defaultTitle
            else -> defaultTitle
        }

        return title
    }
}