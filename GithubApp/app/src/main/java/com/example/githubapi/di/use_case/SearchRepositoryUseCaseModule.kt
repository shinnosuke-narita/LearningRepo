package com.example.githubapi.di.use_case

import com.example.githubapi.domain.impl.SearchRepositoryUseCase
import com.example.githubapi.domain.use_case.ISearchRepositoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchRepositoryUseCaseModule {

    @Binds
    abstract fun bindSearchRepositoryUseCase(
        searchRepositoryUseCaseImpl: SearchRepositoryUseCase
    ): ISearchRepositoryUseCase
}