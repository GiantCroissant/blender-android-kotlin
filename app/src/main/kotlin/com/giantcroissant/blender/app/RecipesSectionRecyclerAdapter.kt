package com.giantcroissant.blender.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by apprentice on 1/24/16.
 */
class RecipesSectionRecyclerAdapter public constructor (val items: List<Recipes>) : RecyclerView.Adapter<RecipesSectionRecyclerAdapter.ViewHolder>() {

    public class ViewHolder public constructor(val c: Context, val v: View) : RecyclerView.ViewHolder(v) {
        public lateinit var textView: TextView

        init {
            textView = v.findViewById(R.id.list_item) as TextView

            v.setOnClickListener(object : View.OnClickListener {
                override fun onClick(vi: View) {
                }
            })
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) : ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.textView.text = items[i].name
    }

    override fun getItemCount() : Int {
        return items.size
    }
}
