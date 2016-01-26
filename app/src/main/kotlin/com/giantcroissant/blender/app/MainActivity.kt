package com.giantcroissant.blender.app

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.TextView
import rx.Observable
import rx.Scheduler
import rx.SingleSubscriber
import rx.Subscriber
import rx.schedulers.Schedulers
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader

import com.google.gson.Gson
import com.github.salomonbrys.*
import com.github.salomonbrys.kotson.fromJson
//import rx.lang.kotlin.observable
//import rx.lang.kotlin.toObservable

//import android.widget.Toolbar


//Observable<String> fetchFromGoogle = Observable.create(new Observable.OnSubscribe<String>() {
//    @Override
//    public void call(Subscriber<? super String> subscriber) {
//        try {
//            String data = fetchData("http://www.google.com");
//            subscriber.onNext(data); // Emit the contents of the URL
//            subscriber.onCompleted(); // Nothing more to emit
//        }catch(Exception e){
//            subscriber.onError(e); // In case there are network errors
//        }
//    }
//});
class MainActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null

    val recipesData = Observable.create(object : Observable.OnSubscribe<String> {
        override fun call(t: Subscriber<in String>?) {
            try {
                val inputStream = assets.open("recipes-data.json")
                val inputStreamReader = InputStreamReader(inputStream)
                val sb = StringBuilder()
                val br = BufferedReader(inputStreamReader)
                var read = br.readLine()
                while(read != null) {
                    sb.append(read)
                    read = br.readLine()
                }
                t?.onNext(sb.toString())
                t?.onCompleted()
            } catch(e: Exception) {
                t?.onError(e)
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cl_activity_main)

        recipesData
                .observeOn(Schedulers.io())
                .map { dataString -> Gson().fromJson<RecipesCollectionDataJsonObject>(dataString) }
                .subscribe(object : Subscriber<RecipesCollectionDataJsonObject>() {
                    override fun onNext(r: RecipesCollectionDataJsonObject?) {
                        System.out.println("Success parsing json")
                        System.out.println(r.toString())
                    }

                    override fun onError(e: Throwable?) {
                        System.out.println(e?.message)
                    }

                    override fun onCompleted() {

                    }
                })

        drawerLayout = findViewById(R.id.drawer_layout) as? DrawerLayout
        val navigationView = findViewById(R.id.navigation_view) as? NavigationView
        navigationView?.let {
            it.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    item.setChecked(true)
                    drawerLayout?.closeDrawers()

                    when(item.itemId) {
                        R.id.navigation_item_recipes_list -> {
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, RecipesSectionFragment.newInstance())
                                    .commit()
                        }
                        R.id.navigation_item_personal_collection -> {
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, PersonalSectionFragment.newInstance())
                                    .commit()
                        }
                        R.id.navigation_item_connection_setting -> {
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, ConnectionSectionFragment.newInstance())
                                    .commit()
                        }
                        R.id.navigation_item_company -> {
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, AboutSectionFragment.newInstance())
                                    .commit()
                        }
                    }

                    return true
                }
            })

            it.setCheckedItem(R.id.navigation_item_recipes_list)
            it.menu.performIdentifierAction(R.id.navigation_item_recipes_list, 0)
        }
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
//        if (id == R.id.action_settings) {
//            return true
//        }

        //if (id == R.id.home)

        when(id) {
            android.R.id.home -> drawerLayout?.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }
}

//class MainActivity : AppCompatActivity() {
//    public class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//        var fragments: MutableList<Fragment> = arrayListOf()
//        var fragmentTitles: MutableList<String> = arrayListOf()
//
//        public fun addFragment(title: String, fragment: Fragment) {
//            fragmentTitles.add(title)
//            fragments.add(fragment)
//        }
//
//        public fun clearAll() {
//            fragments.clear()
//            fragmentTitles.clear()
//        }
//
//        override fun getItem(position: Int) : Fragment {
//            return fragments[position]
//        }
//
//        override fun getCount() : Int {
//            return fragments.size
//        }
//
//        override fun getPageTitle(position: Int) : CharSequence {
//            return fragmentTitles[position]
//        }
//    }
//
//    lateinit var adapter: Adapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.bl_activity_main)
//
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//
//        val viewPager = findViewById(R.id.viewpager) as? ViewPager
//        adapter = Adapter(supportFragmentManager).apply {
//            clearAll()
//            addFragment(getString(R.string.tab_recipes_latest), RecipesSectionFragment.newInstance())
//            addFragment(getString(R.string.tab_recipes_popular), RecipesSectionFragment.newInstance())
//        }
//
//        viewPager?.let {
//            it.adapter = adapter
//        }
//        val tabLayout = findViewById(R.id.tabs) as? TabLayout
//        tabLayout?.setupWithViewPager(viewPager)
//
//        val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
//        val navigationView = (findViewById(R.id.navigation_view) as? NavigationView)?.apply {
//            setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
//                override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                    item.setChecked(true)
//
//
//                    //val adapter = Adapter(supportFragmentManager)
//                    //adapter.clearAll()
//                    adapter.addFragment(getString(R.string.tab_connection_connect), ConnectionSectionFragment.newInstance())
//                    adapter.addFragment(getString(R.string.tab_connection_control), ConnectionSectionFragment.newInstance())
//
//                    //viewPager?.adapter = adapter
//
//                    //val tabLayout = (findViewById(R.id.tabs) as? TabLayout)
//                    //tabLayout?.setupWithViewPager(viewPager)
////                    viewPager?.let {
////                        val adapter = Adapter(supportFragmentManager).apply {
////                            clearAll()
////                            addFragment(getString(R.string.tab_connection_connect), ConnectionSectionFragment.newInstance())
////                            addFragment(getString(R.string.tab_connection_control), ConnectionSectionFragment.newInstance())
////                        }
////                        it.adapter = adapter
////
////                        val tabLayout = (findViewById(R.id.tabs) as? TabLayout)?.apply {
////                            setupWithViewPager(it)
////                        }
////                    }
//
////                    print("onNavigationItemSelected")
////                    when(item.itemId) {
////                        R.id.navigation_item_recipes_list -> {
////                            print("recipes selected")
////                            val viewPager = findViewById(R.id.viewpager) as? ViewPager
////                            viewPager?.let {
////                                val adapter = Adapter(supportFragmentManager).apply {
////                                    clearAll()
////                                    addFragment(getString(R.string.tab_recipes_latest), RecipesSectionFragment.newInstance())
////                                    addFragment(getString(R.string.tab_recipes_popular), RecipesSectionFragment.newInstance())
////                                }
////                                it.adapter = adapter
////
////                                val tabLayout = (findViewById(R.id.tabs) as? TabLayout)?.apply {
////                                    setupWithViewPager(it)
////                                }
////                            }
////                        }
////                        R.id.navigation_item_connection_setting -> {
////                            val viewPager = findViewById(R.id.viewpager) as? ViewPager
////                            viewPager?.let {
////                                val adapter = Adapter(supportFragmentManager).apply {
////                                    clearAll()
////                                    addFragment(getString(R.string.tab_connection_connect), ConnectionSectionFragment.newInstance())
////                                    addFragment(getString(R.string.tab_connection_control), ConnectionSectionFragment.newInstance())
////                                }
////                                it.adapter = adapter
////
////                                val tabLayout = (findViewById(R.id.tabs) as? TabLayout)?.apply {
////                                    setupWithViewPager(it)
////                                }
////                            }
////                        }
////                    }
//
//                    drawerLayout.closeDrawers()
//
//
//                    return true
//                }
//            })
//        }
//
////        val viewPager = findViewById(R.id.viewpager) as? ViewPager
////        viewPager?.let {
////            val adapter = Adapter(supportFragmentManager).apply {
////                addFragment(getString(R.string.tab_recipes_latest), RecipesSectionFragment.newInstance())
////                addFragment(getString(R.string.tab_recipes_popular), RecipesSectionFragment.newInstance())
////            }
////            it.adapter = adapter
////
////            val tabLayout = (findViewById(R.id.tabs) as? TabLayout)?.apply {
////                setupWithViewPager(it)
////            }
////        }
//
////        findViewById(R.id.fragment_container)?.let {
////            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, RecipesSectionFragment.newInstance()).commit()
////        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        //val id = item.itemId
//
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//    }
// }

//class MainActivity : AppCompatActivity() {
//
////    private var drawer: DrawerLayout = null
////    private var toolbar: Toolbar = null
//
//    public class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//        //var fragments = java.util.List<Fragment>()
//        var fragments: MutableList<Fragment> = arrayListOf()
//
//        public fun addFragment(fragment: Fragment) {
//            fragments.add(fragment)
//        }
//
//        override fun getItem(position: Int) : Fragment {
//            //return fragments
//            return fragments.get(position)
//        }
//
//        override fun getCount() : Int {
//            return fragments.size
//        }
//
//        override fun getPageTitle(position: Int) : CharSequence {
//            return ""
//        }
//    }
//
//    lateinit var drawerLayout: DrawerLayout
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//        //supportActionBar.setDisplayShowHomeEnabled(true)
//        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
//        supportActionBar.setDisplayHomeAsUpEnabled(true)
//        supportActionBar.title = getString(R.string.nav_item_recipes_list)
//
//        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
//
//        val navigationView = findViewById(R.id.navigation_view) as NavigationView
//        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
//            override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                item.setChecked(true)
//                drawerLayout.closeDrawers()
//
//                return true
//            }
//        })
//        val viewPager = findViewById(R.id.viewpager) as ViewPager
//        val adapter = Adapter(supportFragmentManager)
//        adapter.addFragment(RecipesListFragment.newInstance())
//        adapter.addFragment(RecipesListFragment.newInstance())
//        viewPager.adapter = adapter
//        val tabLayout = findViewById(R.id.tabs) as TabLayout
//        tabLayout.setupWithViewPager(viewPager)
//    }
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
////
////        val toolbar = findViewById(R.id.toolbar) as Toolbar
////        setSupportActionBar(toolbar)
////
////        //supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
////        supportActionBar.setDisplayHomeAsUpEnabled(true)
////
////        val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
////
////        val navigationView = findViewById(R.id.navigation_view) as NavigationView
////        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
////            override fun onNavigationItemSelected(item: MenuItem): Boolean {
////                item.setChecked(true)
////                drawerLayout.closeDrawers()
////
////                when(item.itemId) {
////                    R.id.navigation_item_recipes_list ->
////                        supportFragmentManager
////                            .beginTransaction()
////                            .replace(R.id.frameLayoutContent, RecipesListFragment.newInstance())
////                            .commit()
////                    R.id.navigation_item_personal_collection ->
////                        supportFragmentManager
////                            .beginTransaction()
////                            .replace(R.id.frameLayoutContent, PersonalCollectionFragment.newInstance())
////                            .commit()
////                    R.id.navigation_item_connection_setting ->
////                        supportFragmentManager
////                            .beginTransaction()
////                            .replace(R.id.frameLayoutContent, ConnectionSettingFragment.newInstance())
////                            .commit()
////                    R.id.navigation_item_company -> {
////                        supportFragmentManager
////                                .beginTransaction()
////                                .replace(R.id.frameLayoutContent, CompanyFragment.newInstance())
////                                .commit()
////
//////                        val companyPagerAdapter = CompanyPagerAdapter(supportFragmentManager)
//////                        val companyViewPager = findViewById(R.id.company_viewpager) as ViewPager
//////                        companyViewPager.adapter = companyPagerAdapter
//////                        val companyTabLayout = findViewById(R.id.company_tablayout) as TabLayout
//////                        companyTabLayout.setupWithViewPager(companyViewPager)
////                    }
////                }
////
////                return true
////            }
////        })
////
//////        val companyPagerAdapter = CompanyPagerAdapter(supportFragmentManager)
//////        val companyViewPager = findViewById(R.id.company_viewpager) as ViewPager
//////        companyViewPager.adapter = companyPagerAdapter
//////        val companyTabLayout = findViewById(R.id.company_tablayout) as TabLayout
//////        companyTabLayout.setupWithViewPager(companyViewPager)
////
//////        val adapter = DesignDemoPagerAdapter(supportFragmentManager)
//////        val viewPager = findViewById(R.id.viewpager) as ViewPager
//////        viewPager.adapter = adapter
//////        val tabLayout = findViewById(R.id.tablayout) as TabLayout
//////        tabLayout.setupWithViewPager(viewPager)
////    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true
////        }
//
//        //if (id == R.id.home)
//
//        when(id) {
//            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//    }
//}
//
//open class RecipesListFragment() : Fragment() {
//    public companion object {
//        public fun newInstance(): RecipesListFragment {
//            val fragment = RecipesListFragment().apply {
//                val args = Bundle().apply {
//                }
//
//                arguments = args
//            }
//
//            return fragment
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val recyclerView = inflater?.inflate(R.layout.fragment_recipes_list, container, false) as RecyclerView
//
//        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
//        recyclerView.adapter = RecipesRecyclerAdapter(
//                activity,
//                listOf(Recipes("Beef soup"), Recipes("Melon Juice")))
//
//        return recyclerView
//    }
//}
//
//open class RecipesRecyclerAdapter public constructor(val context: Context, val items: List<Recipes>) : RecyclerView.Adapter<RecipesRecyclerAdapter.ViewHolder>() {
//    public class ViewHolder public constructor(val v: View) : RecyclerView.ViewHolder(v) {
//        public lateinit var textView: TextView
//        init {
//            //val textView = v.findViewById(R.id.list_item) as TextView
//            textView = v.findViewById(R.id.list_item) as TextView
//
//            //textView.text = "Cool"
//        }
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) : RecipesRecyclerAdapter.ViewHolder {
//        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row, viewGroup, false)
//
//        return ViewHolder(v)
//    }
//
//    override fun onBindViewHolder(viewHolder: RecipesRecyclerAdapter.ViewHolder, i: Int) {
//        //items
//        //viewHolder.
//
//        viewHolder.textView.text = items[i].name
//
//        viewHolder.v.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View) {
//                //val context = v.context
//                val intent = Intent(v.context, RecipesDetailActivity::class.java)
//                v.context.startActivity(intent)
//            }
//        })
//    }
//
//    override fun getItemCount() : Int {
//        //return items
//        //return this.items.size()
//        return items.size
//    }
//}
//
//open class PersonalCollectionFragment() : Fragment() {
//    public companion object {
//        public fun newInstance(): PersonalCollectionFragment {
//            val fragment = PersonalCollectionFragment().apply {
//                val args = Bundle().apply {
//                }
//
//                arguments = args
//            }
//
//            return fragment
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val v = inflater?.inflate(R.layout.fragment_personal_collection, container, false)
//
//        return v
//    }
//}
//
//open class ConnectionSettingFragment() : Fragment() {
//    public companion object {
//        public fun newInstance(): ConnectionSettingFragment {
//            val fragment = ConnectionSettingFragment().apply {
//                val args = Bundle().apply {
//                }
//
//                arguments = args
//            }
//
//            return fragment
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val v = inflater?.inflate(R.layout.fragment_connection_setting, container, false)
//
//        return v
//    }
//}
//
//open class CompanyFragment() : Fragment() {
//    public companion object {
//        public fun newInstance(): CompanyFragment {
//            val fragment = CompanyFragment().apply {
//                val args = Bundle().apply {
//                }
//
//                arguments = args
//            }
//
//            return fragment
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val v = inflater?.inflate(R.layout.fragment_company, container, false)
//
//        val companyTextView = v?.findViewById(R.id.company_textView) as TextView
//        companyTextView.text = "Company"
////        TextView countryTextView = (TextView) view.findViewById(R.id.country_textView);
////        countryTextView.setText(getArguments().getString(COUNTRY));
//        return v
//    }
//}
//
//open class CompanyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//        return CompanyFragment.newInstance()
//    }
//
//    override fun getCount(): Int {
//        return 2
//    }
//
//    override fun getPageTitle(position: Int): CharSequence {
//        return "Tab"
//    }
//}
//
//open class DesignDemoFragment() : Fragment() {
//
//    public companion object {
//        public fun newInstance(tabPosition: Int): DesignDemoFragment {
//            val fragment = DesignDemoFragment().apply {
//                val args = Bundle().apply {
//                    // putInt()
//                }
//
//                arguments = args
//            }
//
//            return fragment
//        }
//    }
//
//    val TAB_POSITION = "tab_position"
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        //return inflater?.inflate(R.layout.hello, container, false)
//
//        val args = getArguments()
//        val tabPosition = arguments.getInt(TAB_POSITION)
//
//        val items = listOf("abc 1", "abc 2", "abc 3", "abc 4", "abc 5", "abc 6", "abc 7", "abc 8", "abc 9", "abc 10")
//
//        val v = inflater?.inflate(R.layout.fragment_list_view, container, false)
//        val recyclerView = v?.findViewById(R.id.recyclerview) as RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerView.adapter = DesignDemoRecyclerAdapter(items)
//
//
////        val textView = TextView(activity)
////        textView.gravity = Gravity.CENTER
////        textView.text = "Text in Tab #" + tabPosition
//
//        return v
//    }
//}
//
//open class DesignDemoPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//        return DesignDemoFragment.newInstance(position)
//    }
//
//    override fun getCount(): Int {
//        return 3
//    }
//
//    override fun getPageTitle(position: Int): CharSequence {
//        return "Tab " + position
//    }
//}
