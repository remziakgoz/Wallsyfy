package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.remziakgoz.wallsyfy.R
import com.remziakgoz.wallsyfy.domain.model.Wallpaper

@Composable
fun LikeAndDownloadInfo(wallpaper: Wallpaper, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(20.dp, bottom = 50.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.romance),
                contentDescription = "Likes Icon",
                tint = Color.Red,
                modifier = modifier.size(30.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = "${wallpaper.likes}",
                color = Color.White
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "Download Icon",
                tint = Color.Red,
                modifier = modifier.size(30.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = "${wallpaper.downloads}",
                color = Color.White
            )
        }
    }
}