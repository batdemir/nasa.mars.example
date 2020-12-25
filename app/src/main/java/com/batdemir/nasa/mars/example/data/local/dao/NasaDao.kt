package com.batdemir.nasa.mars.example.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.batdemir.nasa.mars.example.data.entities.db.PhotosModel

@Dao
interface NasaDao {
    @Query("SELECT * FROM Photos WHERE " +
            "photos.roverName = :roverName " +
            "AND (:sol IS NOT NULL OR photosSol = :sol) " +
            "AND (:camera IS NOT NULL OR cameraName=:camera)")
    fun get(
            roverName: String,
            sol: Int,
            camera: String?,
    ): LiveData<List<PhotosModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(model: PhotosModel)

    @Delete
    suspend fun delete(model: PhotosModel)

    @Update
    suspend fun update(model: PhotosModel)
}