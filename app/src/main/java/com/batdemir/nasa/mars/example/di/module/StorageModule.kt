package com.batdemir.nasa.mars.example.di.module

import com.batdemir.nasa.mars.example.data.storage.SharedPreferencesStorage
import com.batdemir.nasa.mars.example.data.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}