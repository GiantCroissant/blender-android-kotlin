package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by apprentice on 1/25/16.
 */
class ConnectionControlFragment : Fragment() {
    public companion object {
        public fun newInstance(): ConnectionControlFragment {
            val fragment = ConnectionControlFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_connection_control, container, false)

        return view
    }
}