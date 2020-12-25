package com.batdemir.nasa.mars.example.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Rover")
data class RoverModel(
        @PrimaryKey
        @ColumnInfo(name = "roverId") val id: Long,
        @ColumnInfo(name = "roverName") val name: String,
        @SerializedName("landing_date")
        @ColumnInfo(name = "roverLandingDate") val landingDate: String,
        @SerializedName("launch_date")
        @ColumnInfo(name = "roverLaunchDate") val launchDate: String,
        @ColumnInfo(name = "roverStatus") val status: String
)
