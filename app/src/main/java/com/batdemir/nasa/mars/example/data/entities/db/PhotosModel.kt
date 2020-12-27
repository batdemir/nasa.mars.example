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
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as PhotosModel

                if (id != other.id) return false
                if (sol != other.sol) return false
                if (imgSrc != other.imgSrc) return false
                if (earthDate != other.earthDate) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + sol.hashCode()
                result = 31 * result + imgSrc.hashCode()
                result = 31 * result + earthDate.hashCode()
                return result
        }
}

