package com.example.nasa_apod_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.io.Serializable

/**
 * [GalleryInfo] is the data class to represent Gallery information
 */
data class GalleryInfo(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
) : Serializable