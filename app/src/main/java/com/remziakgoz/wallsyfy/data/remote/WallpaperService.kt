package com.remziakgoz.wallsyfy.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WallpaperService : KtorApi() {

    suspend fun getWallpapers(perPage : Int = 10) : WallpapersResponse = client.get {
        pathUrl("api/")
        parameter("order", "latest")
            parameter("per_page", perPage)
    }.body()

    suspend fun searchWallpapers(search : String) : WallpapersResponse = client.get {
        pathUrl("api/")
        parameter("q", search)
    }.body()

    suspend fun getMostDownloadedWallpapers(perPage : Int = 10) : WallpapersResponse = client.get {
        pathUrl("api/")
        parameter("order", "downloads")
        parameter("per_page", perPage)
    }.body()

    suspend fun getMostFavoriteWallpapers(perPage : Int = 10) : WallpapersResponse = client.get {
        pathUrl("api/")
        parameter("order", "likes")
        parameter("per_page", perPage)
    }.body()

}