package com.example.project.data.dto.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
