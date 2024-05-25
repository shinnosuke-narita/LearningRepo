package com.example.project.data.service.api.impl

import com.example.project.data.service.api.PokemonApiService
import com.example.project.data.service.api.model.Request
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.appendPathSegments

class PokeApiServiceImpl(private val httpClient: HttpClient) :
    PokemonApiService {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2"
    }

    override suspend fun get(request: Request): HttpResponse  {
        return httpClient.get(BASE_URL) {
            url {
                appendPathSegments(request.path)
                request.params.forEach { param ->
                    parameters.append(param.key, param.value)
                }
            }
        }
    }
}