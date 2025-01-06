package com.remziakgoz.wallsyfy.presentation.wallpapers.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersScreenState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WallpaperScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

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
        composeTestRule.setContent {
            WallpaperScreen(
                state = WallpapersScreenState(wallpapers = listOf(fakeWallpaper)),
                loadNextWallpapers = {},
                navigateToDetail = {}
            )
        }
    }

    @Test
    fun testWallpaperImageIsDisplayed() {
        composeTestRule.onNodeWithContentDescription(fakeWallpaper.tags, ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun searchBarVisibilityAndInteraction() {
        composeTestRule.onNodeWithContentDescription("Search Bar", ignoreCase = true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Search Bar", ignoreCase = true).performTextInput("Mountain")
    }


}