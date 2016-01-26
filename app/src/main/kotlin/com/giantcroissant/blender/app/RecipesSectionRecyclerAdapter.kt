package com.giantcroissant.blender.app

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * Created by apprentice on 1/24/16.
 */
class RecipesSectionRecyclerAdapter public constructor (val a: AppCompatActivity?, val c: Context, val items: List<Recipes>) : RecyclerView.Adapter<RecipesSectionRecyclerAdapter.ViewHolder>() {

    public class ViewHolder public constructor(val a: AppCompatActivity?, c: Context, val v: View) : RecyclerView.ViewHolder(v) {
        public lateinit var imageView: ImageView
        public lateinit var titleTextView: TextView
        public lateinit var descriptionTextView: TextView
        public var detailButton: Button?
        public var expandButton: Button?

        public var expanded: Boolean = false
        public var description: String = ""

        init {
            imageView = v.findViewById(R.id.recipes_overview_image) as ImageView
            titleTextView = v.findViewById(R.id.title) as TextView
            descriptionTextView = v.findViewById(R.id.description) as TextView
            detailButton = v.findViewById(R.id.recipes_detail) as? Button
            expandButton = v.findViewById(R.id.description_expand_toggle) as? Button

            // This creates a new fragment for listing detail of the recipes
            detailButton?.setOnClickListener { x ->
                Log.d("in view: ", x.toString())

                a?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragment_container, RecipesDetailFragment.newInstance())
                    ?.commit()
            }

            expandButton?.setOnClickListener { x ->
                expanded = !expanded

                descriptionTextView.text = if (expanded) description else ""
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) : ViewHolder {
        //Log.d("flow: ", "onCreateViewHolder")
        //System.out.println("onCreateViewHolder")
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cl_cardview_recipes_overview, viewGroup, false)

        return ViewHolder(a, c, v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.titleTextView.text = items[i].name
        viewHolder.description = items[i].description
        viewHolder.descriptionTextView.text = ""
//        viewHolder.imageView.setImageResource(R.drawable.ic_recipes_stub_image)
        Glide
                .with(c)
                //.placeholder(R.drawable.ic_recipes_stub_image)
//                .load(items[i].imageUrl)
                .load(Uri.parse("file:///android_asset/orange-juice.jpg"))
                //.transform(CircleTransform(c))
                .into(viewHolder.imageView)
    }

    override fun getItemCount() : Int {
        return items.size
    }
}
