package com.batdemir.nasa.example.di.component

import com.batdemir.nasa.example.ui.main.MainFragment
import com.batdemir.nasa.example.ui.main.rover.RoverFragment
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(roverFragment: RoverFragment)
}