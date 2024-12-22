package com.remziakgoz.wallsyfy.presentation.wallpapers

import com.remziakgoz.wallsyfy.domain.model.Wallpaper

data class WallpapersScreenState (
    val wallpapers : List<Wallpaper> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    val search : String? = "Search Wallpapers",
    val refreshing : Boolean = false,
    val loadFinished : Boolean = false
)