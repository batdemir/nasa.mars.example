package com.batdemir.nasa.mars.example.data.entities.response

import com.batdemir.nasa.mars.example.data.entities.db.PhotosModel

data class ResponsePhotosModel(
        val photos: List<PhotosModel>
)