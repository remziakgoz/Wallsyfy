package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.remziakgoz.wallsyfy.R
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.wallpaper_detail.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    viewModel: DetailViewModel = koinViewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        AsyncImage(
            model = wallpaper.largeImageUrl,
            contentDescription = wallpaper.tags,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize()
        )


        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomStart)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.romance),
                    contentDescription = "Likes Icon",
                    tint = Color.Red,
                    modifier = modifier.size(30.dp)
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "${wallpaper.likes}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        shadow = Shadow(
                            color = Color.Black, offset = Offset(1f, 1f), blurRadius = 4f
                        )
                    ),
                    color = Color.White
                )
            }
            Spacer(modifier = modifier.height(8.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.like), // Tık simgesi
                    contentDescription = "Download Icon",
                    tint = Color.Red,
                    modifier = modifier.size(30.dp)
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "${wallpaper.downloads}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        shadow = Shadow(
                            color = Color.Black, offset = Offset(1f, 1f), blurRadius = 4f
                        )

                    ),
                    color = Color.White
                )
            }
        }


        Button(
            onClick = {

            },
            modifier = modifier
                .size(104.dp)
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF9E5A3),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.downloadiconbuble),
                contentDescription = "Download Icon",
                tint = Color.Unspecified,
                modifier = modifier.size(56.dp)
            )
        }
    }
}
