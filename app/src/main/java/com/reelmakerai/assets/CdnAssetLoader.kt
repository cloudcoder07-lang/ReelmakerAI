package com.reelmakerai.assets

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

object CdnAssetLoader {

    suspend fun loadBitmap(path: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val url = URL("${CdnConfig.baseUrl}/$path")
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream = connection.inputStream
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun loadText(path: String): String? = withContext(Dispatchers.IO) {
        try {
            val url = URL("${CdnConfig.baseUrl}/$path")
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()
            connection.inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
