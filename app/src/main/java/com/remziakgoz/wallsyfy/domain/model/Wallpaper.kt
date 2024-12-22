package com.remziakgoz.wallsyfy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Wallpaper(
    val id: Int,
    val tags: String,
    val smallImageUrl: String,
    val largeImageUrl: String,
    val views: Int,
    val downloads: Int,
    val likes: Int
)
