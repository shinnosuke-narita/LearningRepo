package com.example.project.di

import com.example.project.data.repository.GetPokemonIndexRepositoryImpl
import com.example.project.data.service.api.PokemonApiService
import com.example.project.data.service.api.impl.PokeApiServiceImpl
import com.example.project.domain.repository.index.GetPokemonIndexRepository
import com.example.project.domain.usecase.index.GetPokemonIndexUseCase
import com.example.project.domain.usecase.index.impl.GetPokemonIndexInteractor
import com.example.project.presentation.index.IndexScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {
    /** HttpClient */
    single {
        HttpClient(engine = get()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }
    }

    /** service */
    single<PokemonApiService> {
        PokeApiServiceImpl(httpClient = get())
    }

    /** repository */
    single<GetPokemonIndexRepository> {
        GetPokemonIndexRepositoryImpl(api = get() )
    }

    /** use_case */
    single<GetPokemonIndexUseCase> {
        GetPokemonIndexInteractor(apiRepository = get())
    }

    /** ui */
    factory { IndexScreenModel(useCase = get()) }
}