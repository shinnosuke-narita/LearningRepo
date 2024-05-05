package com.example.project.ui.index

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.project.entities.PokemonEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IndexScreenModel : StateScreenModel<IndexScreenState>(IndexScreenState.Init) {
    fun update() {
        screenModelScope.launch {
            mutableState.value = IndexScreenState.Loading
            delay(1000)
            mutableState.value = IndexScreenState.Result(
                listOf(PokemonEntity(0, "pikachu"))
            )
        }
    }
}