package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by apprentice on 1/25/16.
 */
class AboutProductFragment : Fragment() {
    public companion object {
        public fun newInstance(): AboutProductFragment {
            val fragment = AboutProductFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_about_product, container, false) as? RecyclerView

        view?.let {
            it.layoutManager = LinearLayoutManager(it.context)
            it.adapter = AboutProductRecyclerAdapter(
                    (activity as? AppCompatActivity),
                    it.context,
                    listOf(
                            Product("P1", "afdsfdsfds"),
                            Product("P2", "foiewjrowe")))
        }

        return view
    }

}