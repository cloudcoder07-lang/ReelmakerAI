package com.reelmakerai.branding

import android.content.Context
import org.json.JSONObject
import java.io.File

data class BrandingPackMetadata(
    val name: String,
    val version: String,
    val updated: String,
    val fx: List<String>
)

object PackConfig {

    private val fallbackPacks = listOf(
        BrandingPackMetadata("Starter Pack", "1.0", "2025-10-01", listOf("Robot", "Echo")),
        BrandingPackMetadata("Pro Pack", "1.1", "2025-10-02", listOf("Chipmunk", "Deep"))
    )

    fun loadFromManifest(context: Context): List<BrandingPackMetadata> {
        val file = File(context.filesDir, "manifest.json")
        if (!file.exists()) return fallbackPacks

        return try {
            val json = JSONObject(file.readText())
            val array = json.getJSONArray("packs")
            List(array.length()) { i ->
                val name = array.getString(i)
                BrandingPackMetadata(name, "1.0", "2025-10-01", listOf("Voice FX"))
            }
        } catch (e: Exception) {
            fallbackPacks
        }
    }
}
