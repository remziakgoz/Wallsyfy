package com.remziakgoz.wallsyfy.presentation.wallpapers.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersEvent
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersScreenState
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WallpaperScreen(
    modifier: Modifier = Modifier,
    state: WallpapersScreenState,
    loadNextWallpapers: (Boolean) -> Unit,
    navigateToDetail: (Wallpaper) -> Unit,
    viewModel: WallpapersViewModel = koinViewModel()
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.refreshing,
        onRefresh = { loadNextWallpapers(true) }
    )

    val gridState = rememberLazyGridState()
    val isSearchBarVisible = remember { mutableStateOf(true) }

    LaunchedEffect(remember { derivedStateOf { gridState.firstVisibleItemScrollOffset } }) {
        snapshotFlow { gridState.firstVisibleItemScrollOffset }
            .collect { offset ->
                isSearchBarVisible.value = offset == 0
            }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary)
            .pullRefresh(pullRefreshState)
    ) {
        Column {

            AnimatedVisibility(visible = isSearchBarVisible.value) {
                WallpaperSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    hint = "Mountain",
                    onSearch = {
                        viewModel.onEvent(WallpapersEvent.Search(it))
                    }
                )
            }

            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                itemsIndexed(
                    state.wallpapers,
                    key = { _, wallpaper -> wallpaper.id }
                ) { index, wallpaper ->
                    WallpaperListItem(wallpaper = wallpaper, onWallpaperClick = { navigateToDetail(wallpaper) })

                    if (index >= state.wallpapers.size - 1 && !state.isLoading && !state.loadFinished) {
                        LaunchedEffect(key1 = Unit, block = { loadNextWallpapers(false) })
                    }
                }

                if (state.isLoading && state.wallpapers.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(color = Color.Red)
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = state.refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
