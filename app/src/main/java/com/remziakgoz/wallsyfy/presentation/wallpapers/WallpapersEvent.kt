package com.remziakgoz.wallsyfy.presentation.wallpapers


sealed class WallpapersEvent {
    data class Search(val searchString : String) : WallpapersEvent()
}
