package com.batdemir.nasa.example.di.module

import com.batdemir.nasa.example.data.remote.datasource.NasaPagingDataSource
import com.batdemir.nasa.example.data.remote.service.NasaPagingService
import com.batdemir.nasa.example.data.repository.NasaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryNasa(service: NasaPagingService) =
        NasaRepository(service)
}