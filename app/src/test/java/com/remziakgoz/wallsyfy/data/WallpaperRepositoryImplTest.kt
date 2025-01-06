import com.remziakgoz.wallsyfy.data.remote.RemoteDataSource
import com.remziakgoz.wallsyfy.data.remote.WallpapersResponse
import com.remziakgoz.wallsyfy.data.repository.WallpaperRepositoryImpl
import com.remziakgoz.wallsyfy.data.toWallpaperRemote
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class WallpaperRepositoryImplTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var repository: WallpaperRepositoryImpl

    @Mock
    private lateinit var mockRemoteDataSource: RemoteDataSource


    private val testDispatcher = StandardTestDispatcher()

    private val mockWallpapers = listOf(
        Wallpaper(
            id = 1,
            tags = "nature",
            smallImageUrl = "https://cdn.pixabay.com/photo/2025/01/01/21/02/winter-9304339_150.jpg",
            largeImageUrl = "https://pixabay.com/get/g277187520c55e7b06f9ea4c677404e6fcd4a67fe7c72ff9693d8d26dad3771a3f2509c949a9de5b9386158e0b34dfb63e3ed69ecffd2ef4eeeb47b2e2c349857_1280.jpg",
            views = 1500,
            downloads = 500,
            likes = 200
        ),
        Wallpaper(
            id = 2,
            tags = "nature",
            smallImageUrl = "https://cdn.pixabay.com/photo/2025/01/01/21/02/winter-9304339_150.jpg",
            largeImageUrl = "https://pixabay.com/get/g277187520c55e7b06f9ea4c677404e6fcd4a67fe7c72ff9693d8d26dad3771a3f2509c949a9de5b9386158e0b34dfb63e3ed69ecffd2ef4eeeb47b2e2c349857_1280.jpg",
            views = 1500,
            downloads = 500,
            likes = 200
        )
    )

    private val wallpaperRemoteList = mockWallpapers.map { it.toWallpaperRemote() }

    private val wallpapersResponse = WallpapersResponse(hits = wallpaperRemoteList)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = WallpaperRepositoryImpl(mockRemoteDataSource)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `getWallpapers should return list of wallpapers`() = runTest {

        Mockito.`when`(mockRemoteDataSource.getWallpapers(10)).thenReturn(wallpapersResponse)

        val result = repository.getWallpapers(10)

        assertNotNull(result)
        assertEquals(mockWallpapers.size, result.size)
    }

    @Test
    fun `searchWallpapers should return list of wallpapers when search term is provided`() =
        runTest {
            val searchTerm = "nature"
            Mockito.`when`(mockRemoteDataSource.searchWallpapers(searchTerm))
                .thenReturn(wallpapersResponse)

            val result = repository.searchWallpapers(searchTerm)

            assertNotNull(result)
            assertEquals(mockWallpapers.size, result.size)
        }

    @Test
    fun `getMostDownloadedWallpapers should return most downloaded wallpapers`() = runTest {
        Mockito.`when`(mockRemoteDataSource.getMostDownloadedWallpapers(10))
            .thenReturn(wallpapersResponse)
        val result = repository.getMostDownloadedWallpapers(10)
        assertNotNull(result)
        assertEquals(mockWallpapers.size, result.size)
    }

    @Test
    fun `getMostFavoriteWallpapers should return most favorite wallpapers`() = runTest {
        Mockito.`when`(mockRemoteDataSource.getMostFavoriteWallpapers(10))
            .thenReturn(wallpapersResponse)

        val result = repository.getMostFavoriteWallpapers(10)

        assertNotNull(result)
        assertEquals(mockWallpapers.size, result.size)
    }
}
