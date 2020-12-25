package com.batdemir.nasa.mars.example.app

import android.app.Application
import com.batdemir.nasa.mars.example.BuildConfig
import com.batdemir.nasa.mars.example.di.component.ApplicationComponent
import com.batdemir.nasa.mars.example.di.component.DaggerApplicationComponent
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