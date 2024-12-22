package com.remziakgoz.wallsyfy.presentation.wallpaper_detail

data class DetailScreenState (
    val imageSaved : Boolean = false,
    val isDownloading : Boolean = false,
    val showDownloadToast : Boolean = false,
    val error : String? = null
)