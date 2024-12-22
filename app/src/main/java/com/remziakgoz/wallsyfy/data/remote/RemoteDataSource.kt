package com.remziakgoz.wallsyfy.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val apiService: WallpaperService,
) {

    suspend fun getWallpapers(perPage: Int) = withContext(Dispatchers.IO) {
        apiService.getWallpapers(perPage = perPage)
    }

    suspend fun searchWallpapers(search: String) = withContext(Dispatchers.IO) {
        apiService.searchWallpapers(search = search)
    }

}