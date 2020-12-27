package com.batdemir.nasa.mars.example.data.repository

import com.batdemir.nasa.mars.example.data.remote.datasource.NasaRemoteDataSource
import com.batdemir.nasa.mars.example.utils.performGetOperation
import javax.inject.Inject

class NasaRepository @Inject constructor(
        private val remoteDataSource: NasaRemoteDataSource,
) {
    fun get(
        roverName: String,
        sol: Int,
        camera: String?,
        page: Int?
    ) = performGetOperation(
        networkCall = { remoteDataSource.get(roverName, sol, camera, page) }
    )
}