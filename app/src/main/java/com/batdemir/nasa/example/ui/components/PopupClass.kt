package com.batdemir.nasa.example.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import com.batdemir.nasa.example.R
import com.batdemir.nasa.example.data.entities.db.PhotosModel
import com.batdemir.nasa.example.utils.TimeExpressions

class PopupClass @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    val zoomClass: ZoomClass
    private val textViewEditEarthDate: TextView
    private val textViewEditRoverName: TextView
    private val textViewEditCameraName: TextView
    private val textViewEditStatus: TextView
    private val textViewEditLaunchingDate: TextView
    private val textViewEditLandingDate: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_popup, this, true)
        orientation = VERTICAL
        zoomClass = findViewById(R.id.image_view)
        textViewEditEarthDate = findViewById(R.id.text_view_edit_earth_date)
        textViewEditRoverName = findViewById(R.id.text_view_edit_rover_name)
        textViewEditCameraName = findViewById(R.id.text_view_edit_camera_name)
        textViewEditStatus = findViewById(R.id.text_view_edit_status)
        textViewEditLaunchingDate = findViewById(R.id.text_view_edit_launching_date)
        textViewEditLandingDate = findViewById(R.id.text_view_edit_landing_date)
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        if (isGone)
            zoomClass.fitToScreen()
        super.onVisibilityChanged(changedView, visibility)
    }

    fun populateDate(model: PhotosModel) {
        textViewEditEarthDate.text = String.format(
            context.getString(R.string.dynamic_earth_date),
            TimeExpressions.setDateFormat(
                model.earthDate,
                TimeExpressions.DateFormat.SMALL_DATE_FORMAT,
                TimeExpressions.DateFormat.SHOW_DATE_FORMAT
            )
        )
        textViewEditRoverName.text = String.format(
            context.getString(R.string.dynamic_rover_name),
            model.rover.name
        )
        textViewEditCameraName.text = String.format(
            context.getString(R.string.dynamic_camera_name),
            model.camera.name
        )
        textViewEditStatus.text = String.format(
            context.getString(R.string.dynamic_status),
            model.rover.status
        )
        textViewEditLaunchingDate.text = String.format(
            context.getString(R.string.dynamic_launching_date),
            TimeExpressions.setDateFormat(
                model.rover.launchDate,
                TimeExpressions.DateFormat.SMALL_DATE_FORMAT,
                TimeExpressions.DateFormat.SHOW_DATE_FORMAT
            )
        )
        textViewEditLandingDate.text = String.format(
            context.getString(R.string.dynamic_landing_date),
            TimeExpressions.setDateFormat(
                model.rover.landingDate,
                TimeExpressions.DateFormat.SMALL_DATE_FORMAT,
                TimeExpressions.DateFormat.SHOW_DATE_FORMAT
            )
        )
    }
}