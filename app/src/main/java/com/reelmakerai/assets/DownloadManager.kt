package com.reelmakerai.assets

import android.content.Context
import java.io.File
import java.net.URL

object DownloadManager {

    fun downloadPackAsset(context: Context, assetUrl: String, filename: String): File {
        val file = File(context.filesDir, filename)
        URL(assetUrl).openStream().use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return file
    }
}
