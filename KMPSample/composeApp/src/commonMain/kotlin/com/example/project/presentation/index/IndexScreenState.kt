package com.example.project.presentation.index

import com.example.project.entities.PokemonEntity

sealed class IndexScreenState {
    data object Init : IndexScreenState()
    data object Loading : IndexScreenState()
    data class Success(val pokemonList: List<PokemonEntity>) : IndexScreenState()
}