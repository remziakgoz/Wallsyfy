package com.remziakgoz.wallsyfy.presentation.wallpapers.viewmodels

import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.domain.use_cases.GetWallpapersUseCase
import com.remziakgoz.wallsyfy.domain.use_cases.SearchWallpapersUseCase
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class WallpapersViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var getWallpapersUseCase: GetWallpapersUseCase

    @Mock
    private lateinit var searchWallpapersUseCase: SearchWallpapersUseCase

    private lateinit var wallpapersViewModel: WallpapersViewModel

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
        wallpapersViewModel = WallpapersViewModel(getWallpapersUseCase, searchWallpapersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getWallpapers success`() = runTest {
        // Arrange
        val mockWallpapers = listOf(fakeWallpaper)
        val loadFinished = false
        Mockito.`when`(getWallpapersUseCase.invoke(10, emptySet())).thenReturn(mockWallpapers to loadFinished)

        // Act
        wallpapersViewModel.getWallpapers()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(false, wallpapersViewModel.state.value.isLoading)
        assertEquals(mockWallpapers, wallpapersViewModel.state.value.wallpapers)
    }

    @Test
    fun `test getWallpapers failure`() = runTest {
        // Arrange
        val errorMessage = "Couldn't load wallpapers"
        Mockito.`when`(getWallpapersUseCase.invoke(10, emptySet())).thenThrow(RuntimeException(errorMessage))

        // Act
        wallpapersViewModel.getWallpapers()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(false, wallpapersViewModel.state.value.isLoading)
        assertEquals(errorMessage, wallpapersViewModel.state.value.error)
    }

    @Test
    fun `test searchWallpapers success`() = runTest {
        // Arrange
        val searchString = "Nature"
        val mockSearchedWallpapers = listOf(fakeWallpaper)
        val loadFinished = false
        Mockito.`when`(searchWallpapersUseCase.invoke(searchString, emptySet())).thenReturn(mockSearchedWallpapers to loadFinished)

        // Act
        wallpapersViewModel.onEvent(WallpapersEvent.Search(searchString))
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(mockSearchedWallpapers, wallpapersViewModel.state.value.wallpapers)
    }

    @Test
    fun `test searchWallpapers failure`() = runTest {
        // Arrange
        val searchString = "Nature"
        val errorMessage = "Couldn't load wallpapers"
        Mockito.`when`(searchWallpapersUseCase.invoke(searchString, emptySet())).thenThrow(RuntimeException(errorMessage))

        // Act
        wallpapersViewModel.onEvent(WallpapersEvent.Search(searchString))
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(errorMessage, wallpapersViewModel.state.value.error)
    }
}