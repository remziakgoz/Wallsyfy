package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.remziakgoz.wallsyfy.domain.model.Wallpaper

@Composable
fun WallpaperImage(wallpaper: Wallpaper, modifier: Modifier = Modifier) {
    AsyncImage(
        model = wallpaper.largeImageUrl,
        contentDescription = wallpaper.tags,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}