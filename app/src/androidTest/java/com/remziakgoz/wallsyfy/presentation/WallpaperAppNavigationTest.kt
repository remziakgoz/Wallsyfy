package com.remziakgoz.wallsyfy.presentation

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import com.remziakgoz.wallsyfy.presentation.common.Detail
import com.remziakgoz.wallsyfy.presentation.wallpapers.WallpapersScreenState
import com.remziakgoz.wallsyfy.presentation.wallpapers.views.WallpaperScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URLEncoder

class WallpaperAppNavigationTest {

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
            WallpaperApp()

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "wallpaperList") {
                composable("wallpaperList") {
                    WallpaperScreen(
                        state = WallpapersScreenState(wallpapers = listOf(fakeWallpaper)),
                        loadNextWallpapers = {},
                        navigateToDetail = { wallpaper ->
                            val wallpaperJson =
                                URLEncoder.encode(Json.encodeToString(wallpaper), "UTF-8")
                            navController.navigate("${Detail.route}/$wallpaperJson")
                        }
                    )
                }

                composable("${Detail.route}/{wallpaperJson}") { backStackEntry ->
                    Text(fakeWallpaper.tags)
                }
            }


        }
    }

    @Test
    fun testHomeNavigateToHome() {
        composeTestRule.onNodeWithText("Home", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Home", ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun testNavigateToMostFavorite() {
        composeTestRule.onNodeWithText("Most Favorites", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Most Favorites", ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun testNavigateToMostDownloaded() {
        composeTestRule.onNodeWithText("Most Downloaded", ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("Most Downloaded", ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun testWallpaperNavigationToDetail() {
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription(fakeWallpaper.tags, ignoreCase = true).performClick()
        composeTestRule.onNodeWithText(fakeWallpaper.tags, ignoreCase = true).assertIsDisplayed()
    }
}
