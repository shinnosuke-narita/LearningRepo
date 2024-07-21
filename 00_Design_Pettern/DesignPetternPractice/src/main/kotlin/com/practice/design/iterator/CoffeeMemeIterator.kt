package com.practice.design.iterator

class CoffeeMemeIterator(
    private val memo: CoffeeMemo
) : Iterator<DripRecipe> {
    private var index: Int = 0

    override fun hasNext(): Boolean {
        return index < memo.getSize()
    }

    override fun next(): DripRecipe {
        if (!hasNext()){
            throw java.lang.IllegalArgumentException()
        }

        val currentRecipe: DripRecipe = memo.getRecipeByIndex(index)
        index++

        return currentRecipe
    }
}