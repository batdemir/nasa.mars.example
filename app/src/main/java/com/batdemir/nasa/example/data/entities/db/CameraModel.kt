package com.batdemir.nasa.example.data.entities.db

import com.google.gson.annotations.SerializedName

data class CameraModel(
        val id: Long,
        val name: String,
        @SerializedName("rover_id")
        val roverId: Long,
        @SerializedName("full_name")
        val fullName: String
)