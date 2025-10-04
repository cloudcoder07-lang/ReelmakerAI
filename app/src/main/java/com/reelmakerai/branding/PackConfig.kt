package com.reelmakerai.branding

object PackConfig {
    val availablePacks = listOf(
        PackMetadata("Bloom Pack", "1.0", "2025-10-01", listOf("Voice FX", "Collage")),
        PackMetadata("Echo Pack", "1.1", "2025-11-01", listOf("Echo FX", "Photo Editor"))
    )
}
fun loadFromManifest(context: Context): List<PackMetadata> {
    val file = File(context.filesDir, "manifest.json")
    if (!file.exists()) return availablePacks

    val json = JSONObject(file.readText())
    val array = json.getJSONArray("packs")
    return List(array.length()) { i ->
        val name = array.getString(i)
        PackMetadata(name, "1.0", "2025-10-01", listOf("Voice FX"))
    }
}
