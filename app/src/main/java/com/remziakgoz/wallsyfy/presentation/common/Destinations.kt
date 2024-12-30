package com.remziakgoz.wallsyfy.presentation.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Wallpapers : Destination {
    override val title: String
        get() = "Wallsyfy"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}

object Detail : Destination {
    override val title: String
        get() = "Wallpaper Details"
    override val route: String
        get() = "detail"
    override val routeWithArgs: String
        get() = "$route/{wallpaperJson}"

    val arguments = listOf(navArgument("wallpaperJson"){
        type = NavType.StringType
    })
}

object MostFavorites : Destination {
    override val title: String
        get() = "Most Favorites Wallpaper"
    override val route: String
        get() = "favorites"
    override val routeWithArgs: String
        get() = route
}

object MostDownload : Destination {
    override val title: String
        get() = "Most Downloaded Wallpapers"
    override val route: String
        get() = "most_downloaded"
    override val routeWithArgs: String
        get() = route
}

val wallpaperDestinations = listOf(Wallpapers, Detail, MostFavorites, MostDownload)