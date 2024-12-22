package com.remziakgoz.wallsyfy.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.view.WindowInsetsController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.common.Detail
import com.remziakgoz.wallsyfy.presentation.common.WallpaperAppBar
import com.remziakgoz.wallsyfy.presentation.common.Wallpapers
import com.remziakgoz.wallsyfy.presentation.common.wallpaperDestinations
import com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views.DetailScreen
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.views.WallpaperScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import java.net.URLDecoder
import java.net.URLEncoder

@SuppressLint("WrongConstant")
@Composable
fun WallpaperApp(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val activity = context as? android.app.Activity

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowInsetsController = activity?.window?.insetsController
        windowInsetsController?.hide(WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE)
    } else {
        @Suppress("DEPRECATION")
        activity?.window?.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = wallpaperDestinations.find {
        backStackEntry?.destination?.route == it.route || it.routeWithArgs == backStackEntry?.destination?.route
    } ?: Wallpapers

    Scaffold(
        topBar = {
            WallpaperAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }

    ) { paddingValues ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = Wallpapers.routeWithArgs
        ) {
            composable(Wallpapers.routeWithArgs) {
                val wallpapersViewModel: WallpapersViewModel = koinViewModel()
                WallpaperScreen(state = wallpapersViewModel.state.value,
                    loadNextWallpapers = {
                        wallpapersViewModel.getWallpapers(forceReload = it)
                    },
                    navigateToDetail = { wallpaper ->
                        val wallpaperJson = URLEncoder.encode(Json.encodeToString(wallpaper), "UTF-8")
                        navController.navigate("${Detail.route}/$wallpaperJson")
                    }
                )
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments) {
                val wallpaperJson = it.arguments?.getString("wallpaperJson")
                val decodedJson = wallpaperJson?.let { json -> URLDecoder.decode(json, "UTF-8") }
                val wallpaper = decodedJson?.let { json -> Json.decodeFromString<Wallpaper>(json) }


                if (wallpaper != null) {
                    DetailScreen(wallpaper = wallpaper)
                } else {
                    // Hata durumu
                    Text(text = "Error loading details")
                }
            }
        }

    }

}