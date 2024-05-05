package com.example.project.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class KoinInit {
    fun execute(appDeclaration: KoinAppDeclaration) {
        startKoin {
            modules(listOf(commonModule))
            appDeclaration()
        }
    }
}