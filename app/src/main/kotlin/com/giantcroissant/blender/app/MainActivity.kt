package com.giantcroissant.blender.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.Toolbar
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

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
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
