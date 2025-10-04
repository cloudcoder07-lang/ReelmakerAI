package com.reelmakerai.assets

import org.json.JSONObject
import java.io.File

object ManifestValidator {

    fun validate(manifestFile: File): Boolean {
        return try {
            val json = JSONObject(manifestFile.readText())
            json.has("packs") && json.getJSONArray("packs").length() > 0
        } catch (e: Exception) {
            false
        }
    }
}
