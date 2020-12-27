package com.batdemir.nasa.example.data.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}