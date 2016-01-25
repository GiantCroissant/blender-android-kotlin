package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by apprentice on 1/24/16.
 */
class RecipesOverviewFragment : Fragment() {
    public companion object {
        public fun newInstance(): RecipesOverviewFragment {
            val fragment = RecipesOverviewFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_recipes_overview, container, false) as? RecyclerView
        view?.let {
            it.layoutManager = LinearLayoutManager(it.context)
            it.adapter = RecipesSectionRecyclerAdapter(listOf(Recipes("Melon Juice"), Recipes("Apple Juice")))
        }

        return view
    }
}