package com.remziakgoz.wallsyfy.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class WallpapersResponse (
    val hits : List<WallpaperRemote>
)