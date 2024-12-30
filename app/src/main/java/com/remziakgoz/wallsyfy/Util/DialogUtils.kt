package com.remziakgoz.wallsyfy.Util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object DialogUtils {

    fun showSetWallpaperDialog(context: Context, onSetHomeScreen: () -> Unit, onSetBoth: () -> Unit) {
        val options = arrayOf("Set as Home Screen", "Set as Both Home and Lock Screen")

        val builder = AlertDialog.Builder(context)
            .setTitle("Set Wallpaper")
            .setItems(options) { _: DialogInterface, which: Int ->
                when (which) {
                    0 -> onSetHomeScreen()
                    1 -> onSetBoth()
                }
            }
        builder.create().show()
    }
}