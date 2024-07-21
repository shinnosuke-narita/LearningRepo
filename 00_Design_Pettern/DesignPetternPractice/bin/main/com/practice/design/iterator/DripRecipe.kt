package com.practice.design.iterator

class DripRecipe(
    private val country: String,
    private val roast: String,
    private val grindSize: String,
) {
    fun showRecipeInfo() {
        val recipeInfo: String = """
            ====================
            *RecipeInfo
            country: $country
            roast: $roast
            grindSize: $grindSize
            ====================
        """.trimIndent()

        println(recipeInfo)
    }
}