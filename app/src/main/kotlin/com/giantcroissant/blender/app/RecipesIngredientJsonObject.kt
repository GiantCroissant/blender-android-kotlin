package com.giantcroissant.blender.app

/**
 * Created by apprentice on 1/26/16.
 */
data class RecipesIngredientJsonObject(
        val name: String, val exactMeasurement: Boolean, val amount: String,
        val unit: String, val suggestedMeasurement: String) {
}