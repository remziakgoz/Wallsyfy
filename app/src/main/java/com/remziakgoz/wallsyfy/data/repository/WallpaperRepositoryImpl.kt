package com.remziakgoz.wallsyfy.data.repository

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import com.remziakgoz.wallsyfy.data.remote.RemoteDataSource
import com.remziakgoz.wallsyfy.data.util.toWallpaper
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
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

    @SuppressLint("Recycle")
    override suspend fun saveWallpaper(imageUrl: String, fileName: String, context: Context) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp")
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            val outputStream: OutputStream? = resolver.openOutputStream(it)
            val inputStream = URL(imageUrl).openStream()

            outputStream?.use { out ->
                inputStream.use { input ->
                    input.copyTo(out)
                }
            }

        }
    }

    override suspend fun setWallpaper(context: Context, imageUrl: String, type: String) {
        val wallpaperManager = WallpaperManager.getInstance(context)

        val imageLoader = ImageLoader.Builder(context).build()
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()

        val result = withContext(Dispatchers.IO) {
            val response = imageLoader.execute(request)
            if (response is SuccessResult) {
                val bitmap = response.drawable.toBitmap()
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                ByteArrayInputStream(stream.toByteArray())
            } else {
                null
            }
        }
        result?.let { inputStream ->
            when (type) {
                "Home" -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setStream(
                            inputStream,
                            null,
                            true,
                            WallpaperManager.FLAG_SYSTEM
                        )
                    }
                }

                "Lock" -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setStream(
                            inputStream,
                            null,
                            true,
                            WallpaperManager.FLAG_LOCK
                        )
                    }
                }

                "Both" -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setStream(inputStream)
                    }
                }
            }
        }
    }

    override suspend fun getMostDownloadedWallpapers(perPage: Int): List<Wallpaper> {
        return remoteDataSource.getMostDownloadedWallpapers(perPage = perPage).hits.map {
            it.toWallpaper()
        }
    }

    override suspend fun getMostFavoriteWallpapers(perPage: Int): List<Wallpaper> {
        return remoteDataSource.getMostFavoriteWallpapers(perPage = perPage).hits.map {
            it.toWallpaper()
        }
    }

}
