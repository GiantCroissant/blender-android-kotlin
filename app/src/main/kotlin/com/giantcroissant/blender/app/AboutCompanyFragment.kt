package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by apprentice on 1/25/16.
 */
class AboutCompanyFragment : Fragment() {
    public companion object {
        public fun newInstance(): AboutCompanyFragment {
            val fragment = AboutCompanyFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_about_company, container, false)

        return view
    }
}