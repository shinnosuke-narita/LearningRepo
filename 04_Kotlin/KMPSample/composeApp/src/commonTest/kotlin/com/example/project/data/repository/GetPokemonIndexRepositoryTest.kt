package com.example.project.data.repository

import com.example.project.data.service.api.PokemonApiService
import io.mockk.mockk

class GetPokemonIndexRepositoryTest {
    private val apiServiceMock = mockk<PokemonApiService>(relaxed = true)
    private val sut = GetPokemonIndexRepositoryImpl(apiServiceMock)
}