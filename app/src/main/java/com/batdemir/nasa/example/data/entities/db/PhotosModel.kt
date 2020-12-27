package com.batdemir.nasa.example.data.entities.db

import com.google.gson.annotations.SerializedName

data class PhotosModel(
        val id: Long,
        val sol: Long,
        val camera: CameraModel,
        @SerializedName("img_src")
        val imgSrc: String,
        @SerializedName("earth_date")
        val earthDate: String,
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

