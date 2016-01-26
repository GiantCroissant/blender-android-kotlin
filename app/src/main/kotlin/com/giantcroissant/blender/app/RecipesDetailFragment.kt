package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment

/**
 * Created by apprentice on 1/24/16.
 */
class RecipesDetailFragment : Fragment() {
    public companion object {
        public fun newInstance(): RecipesDetailFragment {
            val fragment = RecipesDetailFragment().apply {
                val args = Bundle().apply {
                }

                arguments = args
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.cl_fragment_recipes_detail, container, false)

        val ytpsf = YouTubePlayerSupportFragment.newInstance()
        ytpsf.initialize("Er1cDWIJ1z4", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
                if (!wasRestored) {
                    player.cueVideo("Er1cDWIJ1z4");
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, error: YouTubeInitializationResult) {

            }
        })
        fragmentManager.beginTransaction().replace(R.id.fragment_youtube_player, ytpsf).commit()
//        val toolbar = view?.findViewById(R.id.toolbar) as? Toolbar
//        (activity as? AppCompatActivity)?.let {
//            it.setSupportActionBar(toolbar)
//            it.supportActionBar.title = getString(R.string.nav_item_recipes_list)
//
//            val viewPager = view?.findViewById(R.id.viewpager) as? ViewPager
//            val adapter = RecipesSectionPagerAdapter(it.supportFragmentManager, view?.context)
//            viewPager?.adapter = adapter
//            val tabLayout = view?.findViewById(R.id.tabs) as? TabLayout
//            tabLayout?.setupWithViewPager(viewPager)
//        }

//        mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
//        mYoutubePlayerFragment.initialize(youtubeKey, this);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment);
//        fragmentTransaction.commit();
        return view
    }
}