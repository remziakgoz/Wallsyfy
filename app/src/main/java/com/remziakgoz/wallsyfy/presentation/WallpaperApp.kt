package com.remziakgoz.wallsyfy.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.common.Detail
import com.remziakgoz.wallsyfy.presentation.common.MostDownload
import com.remziakgoz.wallsyfy.presentation.common.MostFavorites
import com.remziakgoz.wallsyfy.presentation.common.WallpaperAppBar
import com.remziakgoz.wallsyfy.presentation.common.WallpaperBottomBar
import com.remziakgoz.wallsyfy.presentation.common.Wallpapers
import com.remziakgoz.wallsyfy.presentation.common.wallpaperDestinations
import com.remziakgoz.wallsyfy.presentation.wallpaper_detail.views.DetailScreen
import com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels.MostDownloadedScreenViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels.MostFavoriteScreenViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels.WallpapersViewModel
import com.remziakgoz.wallsyfy.presentation.wallpapers.views.WallpaperScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import java.net.URLDecoder
import java.net.URLEncoder

@SuppressLint("WrongConstant")
@Composable
fun WallpaperApp(modifier: Modifier = Modifier) {

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
        },
        bottomBar = {
            if (currentScreen.routeWithArgs == Wallpapers.routeWithArgs || currentScreen.routeWithArgs == MostFavorites.routeWithArgs || currentScreen.routeWithArgs == MostDownload.routeWithArgs) {
                WallpaperBottomBar(currentScreen = currentScreen, navController = navController)
            }
        }

    ) { paddingValues ->
        NavHost(
            navController = navController,
            modifier = modifier.padding(paddingValues),
            startDestination = Wallpapers.routeWithArgs
        ) {
            composable(Wallpapers.routeWithArgs) {
                val wallpapersViewModel: WallpapersViewModel = koinViewModel()
                WallpaperScreen(state = wallpapersViewModel.state.value,
                    loadNextWallpapers = {
                        wallpapersViewModel.getWallpapers(forceReload = it)
                    },
                    navigateToDetail = { wallpaper ->
                        val wallpaperJson =
                            URLEncoder.encode(Json.encodeToString(wallpaper), "UTF-8")
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
                    Text(text = "Error loading details")
                }
            }

            composable(MostFavorites.routeWithArgs) {
                val wallpapersViewModel: MostFavoriteScreenViewModel = koinViewModel()
                WallpaperScreen(state = wallpapersViewModel.state.value,
                    loadNextWallpapers = {
                        wallpapersViewModel.getMostFavorite(forceReload = it)
                    },
                    navigateToDetail = { wallpaper ->
                        val wallpaperJson =
                            URLEncoder.encode(Json.encodeToString(wallpaper), "UTF-8")
                        navController.navigate("${Detail.route}/$wallpaperJson")
                    })
            }

            composable(MostDownload.routeWithArgs) {
                val wallpapersViewModel: MostDownloadedScreenViewModel = koinViewModel()
                WallpaperScreen(state = wallpapersViewModel.state.value,
                    loadNextWallpapers = {
                        wallpapersViewModel.getMostDownloaded(forceReload = it)
                    },
                    navigateToDetail = { wallpaper ->
                        val wallpaperJson =
                            URLEncoder.encode(Json.encodeToString(wallpaper), "UTF-8")
                        navController.navigate("${Detail.route}/$wallpaperJson")
                    })
            }

        }

    }
}