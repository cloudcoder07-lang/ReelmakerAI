package com.reelmakerai.export

data class ExportPreset(
    val name: String,
    val lutUrl: String,
    val watermarkUrl: String,
    val voiceFxEnabled: Boolean
)

object ExportPresets {
    val presets = listOf(
        ExportPreset(
            name = "Epic",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_epic.png",
            watermarkUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/watermarks/epic.png",
            voiceFxEnabled = true
        ),
        ExportPreset(
            name = "Romantic",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_romantic.png",
            watermarkUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/watermarks/romantic.png",
            voiceFxEnabled = false
        ),
        ExportPreset(
            name = "Dark",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_dark.png",
            watermarkUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/watermarks/dark.png",
            voiceFxEnabled = true
        )
    )
}
