package com.batdemir.nasa.mars.example.di.component

import com.batdemir.nasa.mars.example.ui.main.MainFragment
import com.batdemir.nasa.mars.example.ui.rover.RoverFragment
import com.batdemir.nasa.mars.example.ui.rover.filter.FilterFragment
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(roverFragment: RoverFragment)
    fun inject(filterFragment: FilterFragment)
}