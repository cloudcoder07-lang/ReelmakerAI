package com.reelmakerai.network

import android.content.Context
import java.io.File

object OfflineCacheManager {

    fun getCachedFile(context: Context, filename: String): File? {
        val file = File(context.cacheDir, filename)
        return if (file.exists()) file else null
    }

    fun saveToCache(context: Context, filename: String, data: ByteArray): File {
        val file = File(context.cacheDir, filename)
        file.writeBytes(data)
        return file
    }
}
