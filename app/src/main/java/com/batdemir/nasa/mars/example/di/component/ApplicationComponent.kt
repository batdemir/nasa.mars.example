package com.batdemir.nasa.mars.example.di.component

import android.content.Context
import com.batdemir.nasa.mars.example.di.module.LocalModule
import com.batdemir.nasa.mars.example.di.module.NetworkModule
import com.batdemir.nasa.mars.example.di.module.RepositoryModule
import com.batdemir.nasa.mars.example.di.module.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        LocalModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        StorageModule::class
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}