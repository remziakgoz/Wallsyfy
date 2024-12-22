package com.remziakgoz.wallsyfy.domain.use_cases

import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.jvm.Throws

class SearchWallpapersUseCase : KoinComponent {
    private val repository : WallpaperRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(search : String) : List<Wallpaper> {
        return repository.searchWallpapers(search = search)
    }
}