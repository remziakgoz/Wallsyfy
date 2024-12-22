package com.remziakgoz.wallsyfy.presentation.wallpapers.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.remziakgoz.wallsyfy.domain.model.Wallpaper

@Composable
fun WallpaperListItem(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    onWallpaperClick: (Wallpaper) -> Unit
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .clickable { onWallpaperClick(wallpaper) },
        shape = RoundedCornerShape(4.dp)
    ) {

        Column {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(model = wallpaper.largeImageUrl, contentDescription = wallpaper.tags,
                    contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
                )
            }
        }

    }

}