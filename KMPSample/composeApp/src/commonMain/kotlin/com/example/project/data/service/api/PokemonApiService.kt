package com.example.project.data.service.api

import com.example.project.data.service.api.model.Request
import io.ktor.client.statement.HttpResponse

interface PokemonApiService {
    suspend fun get(request: Request): HttpResponse
}