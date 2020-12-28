package com.batdemir.nasa.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.nasa.example.data.entities.db.PhotosModel
import com.batdemir.nasa.example.data.remote.datasource.MySearchParams
import com.batdemir.nasa.example.data.remote.datasource.NasaPagingDataSource
import com.batdemir.nasa.example.data.remote.service.NasaPagingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NasaRepository @Inject constructor(
        private val service: NasaPagingService
) {
    fun getPagingDataSource(searchParams: MySearchParams): Flow<PagingData<PhotosModel>> {
        return Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
                pagingSourceFactory = { NasaPagingDataSource(service, searchParams) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 100
    }
}