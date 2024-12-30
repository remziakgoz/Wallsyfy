package com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun     SetWallpaperDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSetWallpaper: (String) -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Set as Wallpaper") },
            text = {
                Column {
                    Text("Where would you like to set the wallpaper?")
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(
                            "Home Screen" to "Home",
                            "Lock Screen" to "Lock",
                            "Both Home and Lock Screen" to "Both"
                        ).forEach { (label, type) ->
                            TextButton(onClick = { onSetWallpaper(type) }) {
                                Text(text = label)
                            }
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}