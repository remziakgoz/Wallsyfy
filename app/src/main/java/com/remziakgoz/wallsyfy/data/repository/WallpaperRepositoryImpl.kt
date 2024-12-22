package com.remziakgoz.wallsyfy.data.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import com.remziakgoz.wallsyfy.data.remote.RemoteDataSource
import com.remziakgoz.wallsyfy.data.util.toWallpaper
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URL

class WallpaperRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : WallpaperRepository {
    override suspend fun getWallpapers(perPage: Int): List<Wallpaper> {
        return remoteDataSource.getWallpapers(perPage = perPage).hits.map {
            it.toWallpaper()
        }
    }

    override suspend fun searchWallpapers(search: String): List<Wallpaper> {
        return remoteDataSource.searchWallpapers(search = search).hits.map {
            it.toWallpaper()
        }
    }
}