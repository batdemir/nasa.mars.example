package com.batdemir.nasa.example.data.entities.response

import com.batdemir.nasa.example.data.entities.db.PhotosModel

data class ResponsePhotosModel(
        val photos: List<PhotosModel>
)