package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomSnackbar(snackbarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackbarHostState) {
        Snackbar(
            snackbarData = it,
            contentColor = Color.Blue,
            containerColor = Color.White,
            actionColor = Color.Red
        )
    }
}