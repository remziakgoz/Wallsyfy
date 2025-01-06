package com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels

import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.use_cases.GetMostDownloadedUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.GetMostFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class MostDownloadedScreenViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var getWallpapersUseCase: GetMostDownloadedUseCase

    @Mock
    private lateinit var viewModel: MostDownloadedScreenViewModel

    private val fakeWallpaper = Wallpaper(
        id = 1,
        tags = "nature",
        smallImageUrl = "https://cdn.pixabay.com/photo/2025/01/01/21/02/winter-9304339_150.jpg",
        largeImageUrl = "https://pixabay.com/get/g277187520c55e7b06f9ea4c677404e6fcd4a67fe7c72ff9693d8d26dad3771a3f2509c949a9de5b9386158e0b34dfb63e3ed69ecffd2ef4eeeb47b2e2c349857_1280.jpg",
        views = 1500,
        downloads = 500,
        likes = 200
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = MostDownloadedScreenViewModel(getWallpapersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMostFavorite success`() = runTest {

        val mockWallpapers = listOf(fakeWallpaper)
        val loadFinished = false
        Mockito.`when`(getWallpapersUseCase.invoke(10, emptySet()))
            .thenReturn(mockWallpapers to loadFinished)

        viewModel.getMostDownloaded()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.state.value.loadFinished)
        assertEquals(mockWallpapers, viewModel.state.value.wallpapers)
    }

    @Test
    fun `test getMostFavorite failure`() = runTest {

        val errorMessage = "Couldn't load wallpapers"
        Mockito.`when`(getWallpapersUseCase.invoke(10, emptySet()))
            .thenThrow(RuntimeException(errorMessage))

        viewModel.getMostDownloaded()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(errorMessage, viewModel.state.value.error)
    }
}