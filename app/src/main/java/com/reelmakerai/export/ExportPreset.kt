package com.reelmakerai.export

data class ExportPreset(
    val resolution: String,
    val bitrateKbps: Int,
    val codec: String
)

object PresetLibrary {
    val default = ExportPreset("720p", 2500, "H.264")
    val highQuality = ExportPreset("1080p", 5000, "H.265")
    val lite = ExportPreset("480p", 1200, "H.264")
}
