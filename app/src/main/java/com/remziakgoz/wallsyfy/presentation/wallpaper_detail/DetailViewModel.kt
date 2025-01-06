package com.remziakgoz.wallsyfy.presentation.wallpaper_detail

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.use_cases.SaveWallpaperUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SetWallpaperUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val saveWallpaperUseCase: SaveWallpaperUseCase,
    private val setWallpaperUseCase: SetWallpaperUseCase
) : ViewModel() {

    fun saveWallpaper(imageUrl: String, fileName: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            saveWallpaperUseCase(imageUrl = imageUrl, fileName = fileName, context = context)
            launch(Dispatchers.Main) {
                Toast.makeText(context, "Wallpaper saved successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setWallpaper(context: Context, imageUrl: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setWallpaperUseCase(context = context, imageUrl = imageUrl, type = type)
        }
    }
}
