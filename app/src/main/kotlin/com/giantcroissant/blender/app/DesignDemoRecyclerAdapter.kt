package com.giantcroissant.blender.app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by apprentice on 1/23/16.
 */
class DesignDemoRecyclerAdapter(items: List<String>) : RecyclerView.Adapter<DesignDemoRecyclerAdapter.ViewHolder>() {
    public class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            val textView = v.findViewById(R.id.list_item) as TextView

            textView.text = "Cool"
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) : ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        //items
        //viewHolder.
    }

    override fun getItemCount() : Int {
        //return items
        //return this.items.size()
        return 15
    }
//    override fun onViewRecycled(holder: ViewHolder?) {
//        super.onViewRecycled(holder)
//    }
}