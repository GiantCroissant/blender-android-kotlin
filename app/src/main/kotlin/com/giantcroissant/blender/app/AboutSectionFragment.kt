package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by apprentice on 1/25/16.
 */
class AboutSectionFragment : Fragment() {
    public companion object {
        public fun newInstance(): AboutSectionFragment {
            val fragment = AboutSectionFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_about_section, container, false)
        val toolbar = view?.findViewById(R.id.toolbar) as? Toolbar
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(toolbar)
            it.supportActionBar.title = getString(R.string.nav_item_company)

            val viewPager = view?.findViewById(R.id.viewpager) as? ViewPager
            val adapter = AboutSectionPagerAdapter(it.supportFragmentManager, view?.context)
            viewPager?.adapter = adapter
            val tabLayout = view?.findViewById(R.id.tabs) as? TabLayout
            tabLayout?.setupWithViewPager(viewPager)
        }

        return view
    }
}