package com.remziakgoz.wallsyfy.data.repository

import android.provider.MediaStore
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.remziakgoz.wallsyfy.data.remote.RemoteDataSource
import com.remziakgoz.wallsyfy.data.repository.WallpaperRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class WallpaperRepositoryTest {

    @Test
    fun testSaveWallpaper() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val repository = WallpaperRepositoryImpl(remoteDataSource = mock(RemoteDataSource::class.java))

        val testImageUrl = "https://cdn.pixabay.com/photo/2025/01/01/21/02/winter-9304339_150.jpg"
        val testFileName = "test_image_${System.currentTimeMillis()}.jpg"

        repository.saveWallpaper(testImageUrl, testFileName, context)

        val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(testFileName)

        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val savedFileName = it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                assertNotNull(savedFileName)
            } else {
                throw AssertionError("Wallpaper was not saved in MediaStore.")
            }
        } ?: throw AssertionError("No entry found in MediaStore for the given file name.")
    }


}
