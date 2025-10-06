package com.reelmakerai.export

/**
 * Centralized list of available export presets.
 */
object ExportPresets {

    val all: List<ExportPreset> = listOf(
        ExportPreset(
            name = "Cinematic",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_cinematic.png"
        ),
        ExportPreset(
            name = "Vintage",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_vintage.png"
        ),
        ExportPreset(
            name = "Epic",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_epic.png"
        ),
        ExportPreset(
            name = "Romantic",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_romantic.png"
        ),
        ExportPreset(
            name = "Dark",
            lutUrl = "https://cdn.jsdelivr.net/gh/cloudcoder07/reelmaker-assets/luts/lut_dark.png"
        )
    )
}
