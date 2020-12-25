package com.batdemir.nasa.mars.example.data.remote.service

import com.batdemir.nasa.mars.example.data.entities.response.ResponsePhotosModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaService {
    @GET("mars-photos/api/v1/rovers/{roverName}/photos")
    suspend fun get(
            @Path("roverName") roverName: String,
            @Query("sol") sol: Int,
            @Query("camera") camera: String?,
            @Query("page") page: Int?,
            @Query("api_key") api: String
    ): Response<ResponsePhotosModel>
}