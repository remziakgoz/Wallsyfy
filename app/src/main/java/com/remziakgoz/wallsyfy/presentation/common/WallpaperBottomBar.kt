package com.remziakgoz.wallsyfy.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remziakgoz.wallsyfy.R
import com.remziakgoz.wallsyfy.presentation.ui.theme.GreenJC

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WallpaperBottomBar(
    modifier: Modifier = Modifier,
    currentScreen: Destination,
    navController: NavController
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .size(75.dp)
    ) {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            val selected = remember { mutableStateOf(currentScreen.routeWithArgs) }

            // Home Button with Text
            IconButton(onClick = {
                selected.value = Wallpapers.routeWithArgs
                navController.navigate(Wallpapers.routeWithArgs) {
                    popUpTo(0)
                }
            }, modifier = modifier.weight(1f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Wallpapers.route) Color.White else Color.Black
                    )
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (selected.value == Wallpapers.route) Color.White else Color.Black
                    )
                }
            }

            // Most Favorites Button with Text
            IconButton(onClick = {
                selected.value = MostFavorites.route
                navController.navigate(MostFavorites.routeWithArgs) {
                    popUpTo(0)
                }
            }, modifier = modifier.weight(1f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Favorite, contentDescription = "Most Favorites", modifier = Modifier.size(26.dp),
                        tint = if (selected.value == MostFavorites.route) Color.White else Color.Black
                    )
                    Text(
                        text = "Most Favorites",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (selected.value == MostFavorites.route) Color.White else Color.Black
                    )
                }
            }

            // Most Downloads Button with Text
            IconButton(onClick = {
                selected.value = MostDownload.route
                navController.navigate(MostDownload.routeWithArgs) {
                    popUpTo(0)
                }
            }, modifier = modifier.weight(1f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painterResource(R.drawable.scrolldown), contentDescription = "Most Downloads", modifier = Modifier.size(26.dp),
                        tint = if (selected.value == MostDownload.route) Color.White else Color.Black
                    )
                    Text(
                        text = "Most Downloaded",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (selected.value == MostDownload.route) Color.White else Color.Black
                    )
                }
            }
        }
    }
}
