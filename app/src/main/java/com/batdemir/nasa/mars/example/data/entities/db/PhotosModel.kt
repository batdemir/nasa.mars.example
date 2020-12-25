package com.batdemir.nasa.mars.example.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Photos")
data class PhotosModel(
        @PrimaryKey
        @ColumnInfo(name = "photosId") val id: Long,
        @ColumnInfo(name = "photosSol") val sol: Long,
        @Embedded
        val camera: CameraModel,
        @SerializedName("img_src")
        @ColumnInfo(name = "photosImgSrc") val imgSrc: String,
        @SerializedName("earth_date")
        @ColumnInfo(name = "photosEarthDate") val earthDate: String,
        @Embedded
        val rover: RoverModel
)
