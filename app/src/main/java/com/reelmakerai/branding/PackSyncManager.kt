package com.reelmakerai.branding

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

object PackSyncManager {

    suspend fun fetchRemotePacks(context: Context): List<PackMetadata> {
        return withContext(Dispatchers.IO) {
            val url = URL("https://cdn.reelmaker.ai/packs/metadata.json")
            val json = url.readText()
            val array = JSONArray(json)
            List(array.length()) { i ->
                val obj = array.getJSONObject(i)
                PackMetadata(
                    name = obj.getString("name"),
                    version = obj.getString("version"),
                    releaseDate = obj.getString("releaseDate"),
                    features = obj.getJSONArray("features").let { f ->
                        List(f.length()) { j -> f.getString(j) }
                    }
                )
            }
        }
    }
}
