package com.remziakgoz.wallsyfy.domain.use_cases

import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.repository.WallpaperRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.jvm.Throws

class GetMostDownloadedUseCase : KoinComponent {
    private val repository: WallpaperRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(
        perPage: Int,
        loadedWallpaperIds: Set<Int>
    ): Pair<List<Wallpaper>, Boolean> {
        val resultWallpapers = repository.getMostDownloadedWallpapers(perPage = perPage)
        val newWallpapers = resultWallpapers.filter { !loadedWallpaperIds.contains(it.id) }
        return Pair(newWallpapers, newWallpapers.isEmpty())
    }
}