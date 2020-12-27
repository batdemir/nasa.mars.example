package com.batdemir.nasa.example.di.component

import android.content.Context
import com.batdemir.nasa.example.di.module.NetworkModule
import com.batdemir.nasa.example.di.module.RepositoryModule
import com.batdemir.nasa.example.di.module.StorageModule
import com.batdemir.nasa.example.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
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

    fun inject(mainActivity: MainActivity)
    fun mainComponent(): MainComponent.Factory
}