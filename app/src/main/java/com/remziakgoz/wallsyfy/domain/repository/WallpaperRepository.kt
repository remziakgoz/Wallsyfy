package com.remziakgoz.wallsyfy.domain.repository

import android.content.Context
import android.graphics.Bitmap
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import java.io.File

interface WallpaperRepository {
    suspend fun getWallpapers(perPage : Int) : List<Wallpaper>
    suspend fun searchWallpapers(search : String) : List<Wallpaper>
    suspend fun saveWallpaper(imageUrl: String, fileName: String, context: Context)
    suspend fun setWallpaper(context: Context, imageUrl: String, type: String)
    suspend fun getMostDownloadedWallpapers(perPage: Int) : List<Wallpaper>
    suspend fun getMostFavoriteWallpapers(perPage: Int) : List<Wallpaper>
}