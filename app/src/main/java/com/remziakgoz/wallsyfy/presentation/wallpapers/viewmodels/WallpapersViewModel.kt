package com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remziakgoz.wallsyfy.domain.use_cases.GetWallpapersUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SearchWallpapersUseCase
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersEvent
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersScreenState
import kotlinx.coroutines.launch

class WallpapersViewModel(
    private val getWallpapersUseCase: GetWallpapersUseCase,
    private val searchWallpapersUseCase: SearchWallpapersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WallpapersScreenState())
    val state: State<WallpapersScreenState> = _state

    private var currentPerPage = 10
    private val loadedWallpaperIds = mutableSetOf<Int>()

    init {
        getWallpapers(forceReload = false)
    }

    fun getWallpapers(forceReload: Boolean = false) {
        if (_state.value.isLoading) return

        if (forceReload) {
            currentPerPage = 10
            loadedWallpaperIds.clear()
            _state.value =
                _state.value.copy(wallpapers = emptyList())
        }

        if (currentPerPage == 10) {
            _state.value = _state.value.copy(refreshing = true)
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val (newWallpapers, loadFinished) = getWallpapersUseCase(perPage = currentPerPage, loadedWallpaperIds = loadedWallpaperIds)
                loadedWallpaperIds.addAll(newWallpapers.map { it.id })

                _state.value = _state.value.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = loadFinished,
                    wallpapers = _state.value.wallpapers + newWallpapers
                )

                currentPerPage += 10

            } catch (e: Throwable) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = true,
                    error = "Couldn't load wallpapers: ${e.localizedMessage}"
                )
            }
        }
    }

    private fun searchWallpapers(search : String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(search = search, wallpapers = emptyList())

            try {
                loadedWallpaperIds.clear()
                currentPerPage = 10

                val (searchedWallpapers, loadFinished) = searchWallpapersUseCase(search = search, loadedWallpaperIds = loadedWallpaperIds)
                loadedWallpaperIds.addAll(searchedWallpapers.map { it.id })

                _state.value = _state.value.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = loadFinished,
                    wallpapers = searchedWallpapers
                )



            } catch (e : Throwable) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = true,
                    error = "Couldn't load wallpapers: ${e.localizedMessage}"
                )
            }
        }
    }



    fun onEvent(event : WallpapersEvent) {
        when(event) {
            is WallpapersEvent.Search -> {
                searchWallpapers(event.searchString)
            }
        }
    }


}
