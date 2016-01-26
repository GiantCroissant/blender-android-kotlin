package com.giantcroissant.blender.app

/**
 * Created by apprentice on 1/26/16.
 */
data class RecipesJsonObject(
        val title: String, val ingredients: List<RecipesIngredientJsonObject>, val steps: List<RecipesStepJsonObject>,
        val description: String, val videoCode: String) {
}