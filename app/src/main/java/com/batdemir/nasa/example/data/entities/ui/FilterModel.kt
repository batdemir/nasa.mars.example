package com.batdemir.nasa.example.data.entities.ui

import android.os.Parcel
import android.os.Parcelable

data class FilterModel(
        val id: Long,
        val title: String,
        val subTitle: String,
        var isSelected: Boolean,
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(subTitle)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterModel> {
        override fun createFromParcel(parcel: Parcel): FilterModel {
            return FilterModel(parcel)
        }

        override fun newArray(size: Int): Array<FilterModel?> {
            return arrayOfNulls(size)
        }
    }
}