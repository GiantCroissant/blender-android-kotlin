package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by apprentice on 1/24/16.
 */
class RecipesSectionFragment : Fragment() {
    public companion object {
        public fun newInstance(): RecipesSectionFragment {
            val fragment = RecipesSectionFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

//    public class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//        override fun getItem(position: Int) : Fragment {
//        }
//
//        override fun getCount() : Int {
//        }
//
//        override fun getPageTitle(position: Int) : CharSequence {
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_recipes_section, container, false)
        val toolbar = view?.findViewById(R.id.toolbar) as? Toolbar
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(toolbar)
            it.supportActionBar.title = getString(R.string.nav_item_recipes_list)
            it.supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
            it.supportActionBar.setDisplayHomeAsUpEnabled(true)

            val viewPager = view?.findViewById(R.id.viewpager) as? ViewPager
            val adapter = RecipesSectionPagerAdapter(it.supportFragmentManager, view?.context)
            viewPager?.adapter = adapter
            val tabLayout = view?.findViewById(R.id.tabs) as? TabLayout
            tabLayout?.setupWithViewPager(viewPager)
        }

            //        val toolbar = findViewById(R.id.toolbar) as Toolbar
            //        setSupportActionBar(toolbar)

            //        val view = inflater?.inflate(R.layout.bl_fragment_recipes_section, container, false) as? RecyclerView
            //        view?.let {
            //            it.layoutManager = LinearLayoutManager(it.context)
            //            it.adapter = RecipesSectionRecyclerAdapter(listOf(Recipes("Melon Juice"), Recipes("Apple Juice")))
            //        }

        return view
    }
}