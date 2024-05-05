package com.example.project.di

import com.example.project.presentation.index.IndexScreenModel
import org.koin.dsl.module

val commonModule = module {
    factory { IndexScreenModel() }
}