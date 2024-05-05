package com.example.project.data.dto.pokemon

import com.example.project.entities.PokemonEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponseDto(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<PokemonDto>?
) {
    fun toPokemonEntityList(): List<PokemonEntity> {
        return results?.map { dto ->
            PokemonEntity(
                name = dto.name,
                url = dto.url
            )
        } ?: emptyList()
    }
}
