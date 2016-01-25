package com.giantcroissant.blender.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AppCompatActivity
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
            //val ac = (activity as? AppCompatActivity)?.apply { applicationContext }
            it.layoutManager = LinearLayoutManager(it.context)
            it.adapter = RecipesSectionRecyclerAdapter(
                    (activity as? AppCompatActivity),
                    it.context,
                    listOf(
                            Recipes("苦瓜降壓汁", "http://goo.gl/gEgYUd", "苦瓜的苦味是因其含類奎寧,可刺激胃酸分泌幫助消化,具豐富維生素C對於預防感冒及調節免疫力有幫助"),
                            Recipes("紅蘿蔔鳳梨汁", "https://i.ytimg.com/vi/H-_5E_uk0tk/default.jpg", "紅蘿蔔與鳳梨都含有大量膳食纖維可促進腸蠕動，加上鳳梨消化酵素幫助蛋白質分解，可提高腸胃消化功能，對便祕、消化不良者有益"),
                            Recipes("酪梨蘋果牛奶", "https://i.ytimg.com/vi/OHU8YuCkitw/default.jpg", "酪梨是少數水果中帶有脂肪的水果，而這些脂肪是可以幫助降低血脂肪的單元不飽和脂肪酸，可幫助脂溶性維生素吸收，富含鉀、鎂對神經傳導、肌肉收縮都有益處"),
                            Recipes("養生杏仁豆漿", "https://i.ytimg.com/vi/CLNkOPyxMAI/default.jpg", "南杏具有降低低密度脂蛋白(壞膽固醇)的好處,具有預防心血管疾病功效、黃豆卵磷脂也具有降血脂作用,因此對高血壓、高血脂、心血管患者皆適宜"),
                            Recipes("百合銀耳漿", "https://i.ytimg.com/vi/H1EdXsPOyBA/default.jpg", "白木耳含鈣、膠質對老年骨質流失、關節退化有修補作用，另多醣體可防癌、膳食纖維有排毒功效，因此白木耳經常用於銀髮族的強身；百合有強化呼吸道與安神效果，適宜老年人食用")))
        }

        return view
    }
}