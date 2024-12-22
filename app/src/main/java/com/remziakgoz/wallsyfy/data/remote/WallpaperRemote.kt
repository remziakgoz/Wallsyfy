package com.remziakgoz.wallsyfy.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WallpaperRemote (
    val id: Int,
    val tags: String,
    @SerialName("previewURL")
    val smallImage: String,
    @SerialName("largeImageURL")
    val largeImage: String,
    val views: Int,
    val downloads: Int,
    val likes: Int
)