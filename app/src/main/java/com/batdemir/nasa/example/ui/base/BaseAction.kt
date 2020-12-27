package com.batdemir.nasa.example.ui.base

import android.os.Bundle

interface BaseAction {
    fun inject()
    fun setupDefinition(savedInstanceState: Bundle?)
    fun setupData()
    fun setupListener()
}