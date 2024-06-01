package com.example.project.presentation.index

import com.example.project.entities.PokemonEntity

sealed class IndexScreenState {
    open val pokemonList: List<PokemonEntity> = emptyList()

    data object Init : IndexScreenState()
    data object Loading : IndexScreenState()
    data class Success(override val pokemonList: List<PokemonEntity>) : IndexScreenState()
}