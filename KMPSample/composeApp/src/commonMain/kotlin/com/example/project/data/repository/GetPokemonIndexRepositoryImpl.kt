package com.example.project.data.repository

import com.example.project.data.dto.pokemon.PokemonResponseDto
import com.example.project.data.service.api.PokemonApiService
import com.example.project.data.service.api.model.Param
import com.example.project.data.service.api.model.Request
import com.example.project.domain.repository.index.GetPokemonIndexRepository
import io.ktor.client.call.body

class GetPokemonIndexRepositoryImpl(
    private val api: PokemonApiService
) : GetPokemonIndexRepository {
    companion object {
        val params = listOf(Param("limit", "20"), Param("offset", "0"))
        val path = listOf("pokemon")
    }

    override suspend fun getIndex(): PokemonResponseDto {
        val request = Request(path, params)
        return api.get(request).body()
    }
}