package com.reelmakerai.assets

import android.content.Context
import java.io.File
import java.io.IOException
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AssetManager {

    // 🔹 Load bundled asset from assets/ folder
    fun listAssets(context: Context, folder: String): List<String> {
        return try {
            context.assets.list(folder)?.toList() ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun loadAsset(context: Context, path: String): ByteArray? {
        return try {
            context.assets.open(path).use { it.readBytes() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // 🔹 Download asset from CDN and cache locally
    suspend fun fetchFromCDN(context: Context, assetName: String, cdnUrl: String): File {
        val cacheDir = File(context.cacheDir, "cdn_assets")
        if (!cacheDir.exists()) cacheDir.mkdirs()

        val assetFile = File(cacheDir, assetName)
        if (assetFile.exists()) return assetFile

        return withContext(Dispatchers.IO) {
            URL(cdnUrl).openStream().use { input ->
                assetFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            assetFile
        }
    }

    // 🔹 Check if cached version exists
    fun getCachedCDNAsset(context: Context, assetName: String): File? {
        val assetFile = File(context.cacheDir, "cdn_assets/$assetName")
        return if (assetFile.exists()) assetFile else null
    }
}
