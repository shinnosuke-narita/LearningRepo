package com.example.project.domain.repository.index

import com.example.project.data.dto.pokemon.PokemonResponseDto

interface GetPokemonIndexRepository {
    suspend fun getIndex(): PokemonResponseDto
}