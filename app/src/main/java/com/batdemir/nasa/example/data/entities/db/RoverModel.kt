package com.batdemir.nasa.example.data.entities.db

import com.google.gson.annotations.SerializedName

data class RoverModel(
        val id: Long,
        val name: String,
        @SerializedName("landing_date")
        val landingDate: String,
        @SerializedName("launch_date")
        val launchDate: String,
        val status: String
)
