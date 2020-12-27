package com.batdemir.nasa.mars.example.data.entities.ui

data class FilterModel(
        val id: Long,
        val title: String,
        val subTitle: String,
        var isSelected: Boolean,
        val isCuriosity: Boolean,
        val isOpportunity: Boolean,
        val isSpirit: Boolean
)