package com.reelmakerai.assets

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

object CdnAssetLoader {

    suspend fun loadImage(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val stream = URL(url).openStream()
                BitmapFactory.decodeStream(stream)
            } catch (e: Exception) {
                null
            }
        }
    }
}
