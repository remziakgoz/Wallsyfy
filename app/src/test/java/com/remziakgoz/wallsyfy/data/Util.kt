package com.remziakgoz.wallsyfy.data

import com.remziakgoz.wallsyfy.data.remote.WallpaperRemote
import com.remziakgoz.wallsyfy.domain.model.Wallpaper

fun Wallpaper.toWallpaperRemote(): WallpaperRemote {
    return WallpaperRemote(
        id = this.id,
        tags = this.tags,
        smallImage = this.smallImageUrl,
        largeImage = this.largeImageUrl,
        views = this.views,
        downloads = this.downloads,
        likes = this.likes
    )
}