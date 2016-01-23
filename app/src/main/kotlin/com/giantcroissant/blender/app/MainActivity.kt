package com.giantcroissant.blender.app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.TextView

//import android.widget.Toolbar

class MainActivity : AppCompatActivity() {

//    private var drawer: DrawerLayout = null
//    private var toolbar: Toolbar = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout

        val navigationView = findViewById(R.id.navigation_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                item.setChecked(true)
                drawerLayout.closeDrawers()
                return true
            }
        })

        val adapter = DesignDemoPagerAdapter(supportFragmentManager)
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter
        val tabLayout = findViewById(R.id.tablayout) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }
}

open class DesignDemoFragment() : Fragment() {

    public companion object {
        public fun newInstance(tabPosition: Int): DesignDemoFragment {
            val fragment = DesignDemoFragment().apply {
                val args = Bundle().apply {
                    // putInt()
                }

                arguments = args
            }

            return fragment
        }
    }

    val TAB_POSITION = "tab_position"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater?.inflate(R.layout.hello, container, false)

        val args = getArguments()
        val tabPosition = arguments.getInt(TAB_POSITION)

        val items = listOf("abc 1", "abc 2", "abc 3", "abc 4", "abc 5", "abc 6", "abc 7", "abc 8", "abc 9", "abc 10")

        val v = inflater?.inflate(R.layout.fragment_list_view, container, false)
        val recyclerView = v?.findViewById(R.id.recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = DesignDemoRecyclerAdapter(items)


//        val textView = TextView(activity)
//        textView.gravity = Gravity.CENTER
//        textView.text = "Text in Tab #" + tabPosition

        return v
    }
}

open class DesignDemoPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return DesignDemoFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Tab " + position
    }
}
