package com.practice.design.iterator

class CoffeeMemo : Iterable<DripRecipe> {
    private val recipes: MutableList<DripRecipe> = mutableListOf()

    fun addRecipe(recipe: DripRecipe) {
        recipes.add(recipe)
    }

    fun getSize(): Int {
        return recipes.size
    }

    fun getRecipeByIndex(index: Int): DripRecipe {
        if (index >= recipes.size) {
            return DripRecipe("", "", "")
        }

        return recipes[index]
    }

    override fun iterator(): Iterator<DripRecipe> {
        return CoffeeMemeIterator(this)
    }
}