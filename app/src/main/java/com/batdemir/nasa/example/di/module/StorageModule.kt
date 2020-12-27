package com.batdemir.nasa.example.di.module

import com.batdemir.nasa.example.data.storage.SharedPreferencesStorage
import com.batdemir.nasa.example.data.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}