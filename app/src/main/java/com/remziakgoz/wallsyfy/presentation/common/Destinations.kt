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

val wallpaperDestinations = listOf(Wallpapers, Detail)