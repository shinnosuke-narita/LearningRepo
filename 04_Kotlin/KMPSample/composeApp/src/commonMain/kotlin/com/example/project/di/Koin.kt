package com.example.project.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration) {
    startKoin {
        modules(listOf(commonModule, platformModule))
        appDeclaration()
    }
}

expect val platformModule: Module
