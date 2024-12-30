package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.remziakgoz.wallsyfy.R
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.wallpaper_detail.DetailViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    wallpaper: Wallpaper,
    viewModel: DetailViewModel = koinViewModel()
) {

    val context = LocalContext.current
    var showWallpaperDialog by remember { mutableStateOf(false) }
    var showDownloadDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = { CustomSnackbar(snackbarHostState = snackbarHostState) },
        content = {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                WallpaperImage(wallpaper = wallpaper)
                LikeAndDownloadInfo(wallpaper = wallpaper,
                    modifier = modifier
                        .align(Alignment.BottomStart))

                Column(
                    modifier = modifier
                        .padding(20.dp)
                        .align(Alignment.BottomEnd),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = {
                            showWallpaperDialog = true
                        },
                        modifier = modifier
                            .size(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFF9E5A3),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(50),
                        elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.backgroundset),
                            contentDescription = "Set background",
                            tint = Color.Unspecified,
                            modifier = modifier.size(56.dp)
                        )

                    }

                    Button(
                        onClick = {
                            showDownloadDialog = true
                        },
                        modifier = modifier
                            .size(70.dp),
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

        }
    )

    SetWallpaperDialog(
        showDialog = showWallpaperDialog,
        onDismiss = { showWallpaperDialog = false },
        onSetWallpaper = { type ->
            scope.launch {
                viewModel.setWallpaper(
                    context = context,
                    imageUrl = wallpaper.largeImageUrl,
                    type = type
                )
                showWallpaperDialog = false
                snackbarHostState.showSnackbar("Wallpaper set for $type")
            }
        }
    )

    SetDownloadDialog(
        showDialog = showDownloadDialog,
        onDismiss = { showDownloadDialog = false },
    ) {
        scope.launch {
            viewModel.saveWallpaper(
                imageUrl = wallpaper.largeImageUrl,
                fileName = wallpaper.id.toString(),
                context = context
            )
            showDownloadDialog = false
        }
    }
}


