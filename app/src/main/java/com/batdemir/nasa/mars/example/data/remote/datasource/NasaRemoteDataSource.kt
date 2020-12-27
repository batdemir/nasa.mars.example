package com.batdemir.nasa.mars.example.data.remote.datasource

import com.batdemir.nasa.mars.example.data.remote.BaseDataSource
import com.batdemir.nasa.mars.example.data.remote.service.NasaService
import com.batdemir.nasa.mars.example.di.module.NetworkModule
import javax.inject.Inject

class NasaRemoteDataSource @Inject constructor(
        private val service: NasaService
) : BaseDataSource() {
    suspend fun get(
        roverName: String,
        sol: Int,
        camera: String?,
        page: Int?
    ) = getResult { service.get(roverName, sol, camera, page, NetworkModule.API_KEY) }
}