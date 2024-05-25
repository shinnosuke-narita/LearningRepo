package com.example.project.domain.usecase.index

import com.example.project.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface GetPokemonIndexUseCase {
    suspend fun handle(): Flow<List<PokemonEntity>>
}