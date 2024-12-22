package com.remziakgoz.wallsyfy.presentation.wallpaper_detail

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.remziakgoz.wallsyfy.domain.model.Wallpaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class DetailViewModel(
    private val application: Application
) : ViewModel() {

    private val _state = mutableStateOf(DetailScreenState())
    val state: State<DetailScreenState> = _state


    private fun persistImage(context: Context, file: File, bitmap: Bitmap) {
        val os: OutputStream
        try {
            os = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()

            MediaScannerConnection.scanFile(
                context, arrayOf(file.toString()),
                null, null
            )

            _state.value = _state.value.copy(isDownloading = false)
            _state.value = _state.value.copy(imageSaved = true)
            _state.value = _state.value.copy(showDownloadToast = true)
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error writing bitmap", e)
        }
    }


    fun downloadImageFromUrl1(context: Context, url: String, uniqueId: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false) // Disable hardware bitmaps.
                .build()

            val result = (loader.execute(request) as SuccessResult).drawable

            val bitmap = (result as BitmapDrawable).bitmap

            val path =
                File(application.filesDir.toString() + "/Folder")

            if (!path.exists())
                path.mkdirs()


            val fileName = "$uniqueId.jpg"
            val imageFile = File(path, fileName)

            if (imageFile.exists()) {
                Log.d("DetailViewModel", "File already exists: $fileName")
            } else {
                persistImage(context, imageFile, bitmap)
            }
        }
    }


}