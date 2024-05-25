package com.example.project.di

import com.example.project.RocketLaunchViewModel
import com.example.project.SpaceXSDK
import com.example.project.cache.AndroidSqliteDriver
import com.example.project.network.SpaceXApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = AndroidSqliteDriver(androidContext()),
            api = get()
        )
    }
    viewModel { RocketLaunchViewModel(sdk = get()) }
}