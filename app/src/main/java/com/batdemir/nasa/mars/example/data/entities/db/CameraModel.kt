package com.batdemir.nasa.mars.example.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Camera")
data class CameraModel(
        @PrimaryKey
        @ColumnInfo(name = "cameraId") val id: Long,
        @ColumnInfo(name = "cameraName") val name: String,
        @SerializedName("rover_id")
        @ColumnInfo(name = "cameraRoverId") val roverId: Long,
        @SerializedName("full_name")
        @ColumnInfo(name = "cameraFullName") val fullName: String
)