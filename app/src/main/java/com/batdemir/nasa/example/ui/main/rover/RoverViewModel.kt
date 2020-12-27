package com.batdemir.nasa.example.ui.main.rover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.batdemir.nasa.example.data.entities.db.PhotosModel
import com.batdemir.nasa.example.data.entities.ui.FilterModel
import com.batdemir.nasa.example.data.remote.datasource.MySearchParams
import com.batdemir.nasa.example.data.repository.NasaRepository
import kotlinx.coroutines.flow.Flow
import java.security.InvalidKeyException
import javax.inject.Inject

class RoverViewModel @Inject constructor(
    private val repository: NasaRepository
) : ViewModel() {
    private var _index: Int = -1
    var _isDynamic: Boolean = true
    private val _filterData: MutableLiveData<List<FilterModel>> =
        MutableLiveData<List<FilterModel>>().apply {
            value = listOf(
                FilterModel(
                    1,
                    "FHAZ",
                    "Front Hazard Avoidance Camera",
                    isSelected = false
                ),
                FilterModel(
                    2,
                    "RHAZ",
                    "Rear Hazard Avoidance Camera",
                    isSelected = false
                ),
                FilterModel(
                    3,
                    "MAST",
                    "Mast Camera",
                    isSelected = false
                ),
                FilterModel(
                    4,
                    "MAHLI",
                    "Mars Hand Lens Imager",
                    isSelected = false
                ),
                FilterModel(
                    5,
                    "MARDI",
                    "Mars Descent Imager",
                    isSelected = false
                ),
                FilterModel(
                    6,
                    "NAVCAM",
                    "Navigation Camera",
                    isSelected = false
                ),
                FilterModel(
                    7,
                    "PANCAM",
                    "Panoramic Camera",
                    isSelected = false
                ),
                FilterModel(
                    8,
                    "MINITES",
                    "Miniature Thermal Emission Spectrometer (Mini-TES)",
                    isSelected = false
                )
            )
        }

    val filterData: LiveData<List<FilterModel>> = _filterData

    fun setIndex(value: Int) {
        _index = value
    }

    fun setDynamic(value: Boolean) {
        _isDynamic = value
    }

    fun loadData(): Flow<PagingData<PhotosModel>> {
        val cameraName =
            if (_isDynamic) {
                if (_filterData.value!!.filter { x -> x.isSelected }.count() > 0)
                    _filterData.value!!.first { x -> x.isSelected }.title
                else
                    null
            } else
                null
        return when (_index) {
            RoverType.CURIOSITY_INDEX.value -> {
                repository.getPagingDataSource(
                    MySearchParams(
                        RoverType.CURIOSITY_INDEX.strValue,
                        cameraName
                    )
                )
            }

            RoverType.OPPORTUNITY_INDEX.value -> {
                repository.getPagingDataSource(
                    MySearchParams(
                        RoverType.OPPORTUNITY_INDEX.strValue,
                        cameraName
                    )
                )
            }

            RoverType.SPIRIT_INDEX.value -> {
                repository.getPagingDataSource(
                    MySearchParams(
                        RoverType.SPIRIT_INDEX.strValue,
                        cameraName
                    )
                )
            }
            else -> throw InvalidKeyException("index not found")
        }
    }
}