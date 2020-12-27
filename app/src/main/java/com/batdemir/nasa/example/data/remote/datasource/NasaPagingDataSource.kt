package com.batdemir.nasa.example.data.remote.datasource

import androidx.paging.PagingSource
import com.batdemir.nasa.example.data.entities.db.PhotosModel
import com.batdemir.nasa.example.data.remote.service.NasaPagingService
import com.batdemir.nasa.example.di.module.NetworkModule
import com.bumptech.glide.load.HttpException
import java.io.IOException

class NasaPagingDataSource(
    private val service: NasaPagingService,
    private val searchParams: MySearchParams
) : PagingSource<MyLoadParams, PhotosModel>() {
    override suspend fun load(params: LoadParams<MyLoadParams>): LoadResult<MyLoadParams, PhotosModel> {
        return try {
            val key = params.key ?: MyLoadParams(START_PAGE, START_SOL)
            val response = service.get(
                searchParams.roverName,
                key.sol,
                searchParams.cameraName,
                key.page,
                NetworkModule.API_KEY
            )
            val prevKey =
                when {
                    key.page > 1 -> MyLoadParams(key.page - 1, key.sol, false)
                    key.isSolIncrease -> MyLoadParams(key.page, key.sol - 1, false)
                    else -> null
                }
            val nextKey =
                when {
                    response.photos.isNotEmpty() -> MyLoadParams(
                        key.page + 1,
                        key.sol,
                        isSolIncrease = false
                    )
                    response.photos.isNullOrEmpty() -> MyLoadParams(
                        START_PAGE,
                        key.sol + 1,
                        isSolIncrease = true
                    )
                    else -> null
                }
            LoadResult.Page(
                data = response.photos,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

data class MyLoadParams(
    var page: Int,
    var sol: Int,
    var isSolIncrease: Boolean = false
)

data class MySearchParams(
    val roverName: String,
    val cameraName: String?
)

private const val START_PAGE: Int = 1
private const val START_SOL: Int = 1