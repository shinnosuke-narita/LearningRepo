package com.example.project.domain.usecase.index.impl

import com.example.project.domain.repository.index.GetPokemonIndexRepository
import com.example.project.domain.usecase.index.GetPokemonIndexUseCase
import com.example.project.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonIndexInteractor(
    private val apiRepository: GetPokemonIndexRepository
) : GetPokemonIndexUseCase {
    override suspend fun handle(): Flow<List<PokemonEntity>> {
        return flow {
            val responseDto = apiRepository.getIndex()
            emit(responseDto.toPokemonEntityList())
        }
    }
}