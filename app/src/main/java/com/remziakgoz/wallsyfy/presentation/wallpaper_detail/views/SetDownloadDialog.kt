package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SetDownloadDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onDownload: () -> Unit
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Download Wallpaper") },
            text = { Text("Do you want to download this wallpaper?") },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDownload()
                        onDismiss()
                    }
                ) {
                    Text("Download")
                }
            }
        )

    }
}