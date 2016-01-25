package com.giantcroissant.blender.app

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by apprentice on 1/25/16.
 */
class AboutProductRecyclerAdapter public constructor (val a: AppCompatActivity?, val c: Context, val items: List<Product>) : RecyclerView.Adapter<AboutProductRecyclerAdapter.ViewHolder>() {
    public class ViewHolder public constructor(val a: AppCompatActivity?, c: Context, val v: View) : RecyclerView.ViewHolder(v) {
        public lateinit var titleTextView: TextView
        public lateinit var descriptionTextView: TextView
        init {
            titleTextView = v.findViewById(R.id.title) as TextView
            descriptionTextView = v.findViewById(R.id.description) as TextView
//            detailButton = v.findViewById(R.id.recipes_detail) as? Button
//
//            detailButton?.setOnClickListener { v ->
//                Log.d("in view: ", v.toString())
//
//                a?.supportFragmentManager
//                        ?.beginTransaction()
//                        ?.replace(R.id.fragment_container, RecipesDetailFragment.newInstance())
//                        ?.commit()
//            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) : AboutProductRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cl_cardview_about_product, viewGroup, false)

        return AboutProductRecyclerAdapter.ViewHolder(a, c, v)
    }

    override fun onBindViewHolder(viewHolder: AboutProductRecyclerAdapter.ViewHolder, i: Int) {
        items[i].let {
            viewHolder.titleTextView.text = it.name
            viewHolder.descriptionTextView.text = it.description
        }
    }

    override fun getItemCount() : Int {
        return items.size
    }
}