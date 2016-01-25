package com.giantcroissant.blender.app

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by apprentice on 1/24/16.
 */
class RecipesSectionPagerAdapter(fm: FragmentManager, val c: Context?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return RecipesOverviewFragment.newInstance()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        val defaultTitle = ""
        val title = when(position) {
            0 -> c?.resources?.getString(R.string.tab_recipes_latest) ?: defaultTitle
            1 -> c?.resources?.getString(R.string.tab_recipes_popular) ?: defaultTitle
            else -> defaultTitle
        }

        return title
    }
}
