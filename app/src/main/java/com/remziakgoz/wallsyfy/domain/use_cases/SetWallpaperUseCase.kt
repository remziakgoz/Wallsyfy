package com.remziakgoz.wallsyfy.domain.use_cases

import android.content.Context
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SetWallpaperUseCase : KoinComponent {
    private val repository : WallpaperRepository by inject()

    suspend operator fun invoke(context: Context, imageUrl: String, type: String) {
        repository.setWallpaper(context = context, imageUrl = imageUrl, type = type)
    }

}