package com.batdemir.nasa.mars.example.di.module

import com.batdemir.nasa.mars.example.data.remote.datasource.NasaRemoteDataSource
import com.batdemir.nasa.mars.example.data.repository.NasaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryNasa(
            remoteDataSource: NasaRemoteDataSource,
    ) =
            NasaRepository(remoteDataSource)
}