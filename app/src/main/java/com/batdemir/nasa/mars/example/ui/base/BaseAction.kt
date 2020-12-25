package com.batdemir.nasa.mars.example.ui.base

import android.os.Bundle

interface BaseAction {
    fun setupDefinition(savedInstanceState: Bundle?)
    fun setupData()
    fun setupListener()
}