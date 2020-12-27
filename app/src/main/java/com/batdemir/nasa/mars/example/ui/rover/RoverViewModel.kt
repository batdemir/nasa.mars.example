package com.batdemir.nasa.mars.example.ui.rover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batdemir.nasa.mars.example.data.entities.db.PhotosModel
import com.batdemir.nasa.mars.example.data.entities.response.ResponsePhotosModel
import com.batdemir.nasa.mars.example.data.repository.NasaRepository
import com.batdemir.nasa.mars.example.utils.Resource
import java.security.InvalidKeyException
import javax.inject.Inject

class RoverViewModel @Inject constructor(
        private val repository: NasaRepository
) : ViewModel() {
    private var _index: Int = -1
    private var _response: List<PhotosModel> = listOf()

    private val _data: MutableLiveData<List<PhotosModel>> =
            MutableLiveData<List<PhotosModel>>().apply {
                value = listOf()
            }

    val data: LiveData<List<PhotosModel>> = _data

    fun setIndex(value: Int) {
        _index = value
    }

    fun setData(response: List<PhotosModel>) {
        _response = response
    }

    fun loadData(): LiveData<Resource<ResponsePhotosModel>> {
        return when (_index) {
            RoverType.CURIOSITY_INDEX.value -> {
                repository.get(RoverType.CURIOSITY_INDEX.strValue, 1, null, 1)
            }

            RoverType.OPPORTUNITY_INDEX.value -> {
                repository.get(RoverType.OPPORTUNITY_INDEX.strValue, 1, null, 1)
            }

            RoverType.SPIRIT_INDEX.value -> {
                repository.get(RoverType.SPIRIT_INDEX.strValue, 1, null, 1)
            }
            else -> throw InvalidKeyException("index not found")
        }
    }
}