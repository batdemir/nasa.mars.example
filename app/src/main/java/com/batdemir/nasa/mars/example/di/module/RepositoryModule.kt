package com.batdemir.nasa.mars.example.di.module

import com.batdemir.nasa.mars.example.data.local.datasource.NasaLocalDataSource
import com.batdemir.nasa.mars.example.data.remote.datasource.NasaRemoteDataSource
import com.batdemir.nasa.mars.example.data.repository.NasaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryGithub(
            remoteDataSource: NasaRemoteDataSource,
            localeDataSource: NasaLocalDataSource,
    ) =
            NasaRepository(remoteDataSource, localeDataSource)
}