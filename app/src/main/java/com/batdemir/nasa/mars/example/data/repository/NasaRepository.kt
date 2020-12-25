package com.batdemir.nasa.mars.example.data.repository

import com.batdemir.nasa.mars.example.data.local.datasource.NasaLocalDataSource
import com.batdemir.nasa.mars.example.data.remote.datasource.NasaRemoteDataSource
import com.batdemir.nasa.mars.example.utils.performGetOperation
import javax.inject.Inject

class NasaRepository @Inject constructor(
        private val remoteDataSource: NasaRemoteDataSource,
        private val localDataSource: NasaLocalDataSource
) {
    fun get(
            roverName: String,
            sol: Int,
            camera: String?,
            page: Int?,
            apiKey: String
    ) = performGetOperation(
            databaseQuery = { localDataSource.get(roverName, sol, camera) },
            networkCall = { remoteDataSource.get(roverName, sol, camera, page, apiKey) },
            saveCallResult = {
                for (item in it.photos) {
                    localDataSource.add(item)
                }
            }
    )
}