package com.batdemir.nasa.example.app

import android.app.Application
import com.batdemir.nasa.example.BuildConfig
import com.batdemir.nasa.example.di.component.ApplicationComponent
import com.batdemir.nasa.example.di.component.DaggerApplicationComponent
import timber.log.Timber

class MyApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}