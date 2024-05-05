package com.example.project.presentation.index

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.project.domain.usecase.index.GetPokemonIndexUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class IndexScreenModel(
    val useCase: GetPokemonIndexUseCase
) : StateScreenModel<IndexScreenState>(IndexScreenState.Init) {
    fun getIndex() {
        screenModelScope.launch(Dispatchers.IO) {
            mutableState.emit(IndexScreenState.Loading)
            useCase.handle().collect { res ->
                mutableState.emit(IndexScreenState.Success(res))
            }
        }
    }
}