package com.reelmakerai.assets

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

object AssetSyncManager {

    suspend fun syncFromGitHub(context: Context): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL("https://raw.githubusercontent.com/cloudcoder07-lang/ReelMakerAI-assets/main/manifest.json")
                val manifest = url.readText()
                val file = File(context.filesDir, "manifest.json")
                file.writeText(manifest)
                Log.d("AssetSync", "Manifest synced")
                true
            } catch (e: Exception) {
                Log.e("AssetSync", "Failed to sync: ${e.message}")
                false
            }
        }
    }
}
