package com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remziakgoz.wallsyfy.domain.use_cases.GetMostDownloadedUseCase
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersScreenState
import kotlinx.coroutines.launch

class MostDownloadedScreenViewModel(
    private val getMostDownloadedUseCase: GetMostDownloadedUseCase,
) : ViewModel(){
    private val _state = mutableStateOf(WallpapersScreenState())
    val state: State<WallpapersScreenState> = _state

    private var currentPerPage = 10
    private val loadedWallpaperIds = mutableSetOf<Int>()

    init {
        getMostDownloaded(forceReload = false)
    }

    fun getMostDownloaded(forceReload: Boolean = false) {
        if (_state.value.isLoading) return

        if (forceReload) {
            currentPerPage = 10
            loadedWallpaperIds.clear()
            _state.value = _state.value.copy(wallpapers = emptyList())
        }

        if (currentPerPage == 10) {
            _state.value = _state.value.copy(refreshing = true)
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val (newWallpapers, loadFinished) = getMostDownloadedUseCase(perPage = currentPerPage, loadedWallpaperIds = loadedWallpaperIds)
                loadedWallpaperIds.addAll(newWallpapers.map { it.id })
                _state.value = _state.value.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = loadFinished,
                    wallpapers = _state.value.wallpapers + newWallpapers
                )

                currentPerPage += 10

            } catch (e : Throwable) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = true,
                    error = "Couldn't load wallpapers"
                )
            }
        }

    }
}