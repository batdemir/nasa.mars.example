package com.batdemir.nasa.mars.example.ui.rover.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batdemir.nasa.mars.example.data.entities.ui.FilterModel
import javax.inject.Inject

class FilterViewModel @Inject constructor() : ViewModel() {
    val data: LiveData<List<FilterModel>> = MutableLiveData<List<FilterModel>>().apply {
        value = listOf(
                FilterModel(1, "FHAZ", "Front Hazard Avoidance Camera", isSelected = false, isCuriosity = true, isOpportunity = true, isSpirit = true),
                FilterModel(1, "RHAZ", "Rear Hazard Avoidance Camera", isSelected = false, isCuriosity = true, isOpportunity = true, isSpirit = true),
                FilterModel(1, "MAST", "Mast Camera", isSelected = false, isCuriosity = true, isOpportunity = false, isSpirit = false),
                FilterModel(1, "CHEMCAM", "Chemistry and Camera Complex", isSelected = false, isCuriosity = true, isOpportunity = false, isSpirit = false),
                FilterModel(1, "MAHLI", "Mars Hand Lens Imager", isSelected = false, isCuriosity = true, isOpportunity = false, isSpirit = false),
                FilterModel(1, "MARDI", "Mars Descent Imager", isSelected = false, isCuriosity = true, isOpportunity = false, isSpirit = false),
                FilterModel(1, "NAVCAM", "Navigation Camera", isSelected = false, isCuriosity = true, isOpportunity = true, isSpirit = true),
                FilterModel(1, "PANCAM", "Panoramic Camera", isSelected = false, isCuriosity = false, isOpportunity = true, isSpirit = true),
                FilterModel(1, "MINITES", "Miniature Thermal Emission Spectrometer (Mini-TES)", isSelected = false, isCuriosity = false, isOpportunity = true, isSpirit = true)
        )
    }
}