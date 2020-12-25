package com.batdemir.nasa.mars.example.data.local.datasource

import com.batdemir.nasa.mars.example.data.entities.db.PhotosModel
import com.batdemir.nasa.mars.example.data.local.dao.NasaDao
import javax.inject.Inject

class NasaLocalDataSource @Inject constructor(
        private val dao: NasaDao
) {
    fun get(
            roverName: String,
            sol: Int,
            camera: String?,
    ) = dao.get(roverName, sol, camera)

    suspend fun add(model: PhotosModel) = dao.add(model)
}