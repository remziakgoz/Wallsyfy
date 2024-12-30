package com.remziakgoz.wallsyfy.domain.use_cases

import android.content.Context
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveWallpaperUseCase : KoinComponent {
    private val repository: WallpaperRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(imageUrl: String, fileName: String, context: Context) {
        repository.saveWallpaper(imageUrl = imageUrl, fileName = fileName, context = context)
    }

}