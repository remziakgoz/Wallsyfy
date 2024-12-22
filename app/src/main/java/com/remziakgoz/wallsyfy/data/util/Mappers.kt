package com.remziakgoz.wallsyfy.data.util

import com.remziakgoz.wallsyfy.data.remote.WallpaperRemote
import com.remziakgoz.wallsyfy.domain.model.Wallpaper

fun WallpaperRemote.toWallpaper() : Wallpaper {
    return Wallpaper(
        id = id,
        tags = tags,
        smallImageUrl = smallImage,
        largeImageUrl = largeImage,
        views = views,
        downloads = downloads,
        likes = likes
    )
}