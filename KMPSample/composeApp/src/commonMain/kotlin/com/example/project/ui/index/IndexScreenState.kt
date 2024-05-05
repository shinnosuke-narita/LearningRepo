package com.example.project.ui.index

import com.example.project.entities.PokemonEntity

sealed class IndexScreenState {
    data object Init : IndexScreenState()
    data object Loading : IndexScreenState()
    data class Result(val pokemonList: List<PokemonEntity>) : IndexScreenState()
}