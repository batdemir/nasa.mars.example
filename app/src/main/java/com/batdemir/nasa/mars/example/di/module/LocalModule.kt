package com.batdemir.nasa.mars.example.di.module

import android.content.Context
import com.batdemir.nasa.mars.example.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.getDatabase(context)

    //////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideDaoNasa(db: AppDatabase) = db.nasaDao()

    //////////////////////////////////////////////////////
}