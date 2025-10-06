package com.reelmakerai.assets

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.reelmakerai.network.OfflineCacheManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

object CdnAssetLoader {

    suspend fun loadImage(context: Context, url: String, filename: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                // Check cache first
                val cached = OfflineCacheManager.getCachedFile(context, filename)
                if (cached != null) {
                    Log.d("CdnAssetLoader", "Loaded from cache: $filename")
                    return@withContext BitmapFactory.decodeFile(cached.absolutePath)
                }

                // Download from CDN
                val stream = URL(url).openStream()
                val bytes = stream.readBytes()
                val file = OfflineCacheManager.saveToCache(context, filename, bytes)
                Log.d("CdnAssetLoader", "Downloaded and cached: $filename")
                BitmapFactory.decodeFile(file.absolutePath)

            } catch (e: Exception) {
                Log.e("CdnAssetLoader", "Failed to load image: ${e.message}")
                null
            }
        }
    }
}
