package com.reelmakerai.branding

object PackUpdateManager {

    private var currentPack: PackMetadata = PackMetadata(
        name = "Bloom Pack",
        version = "1.0",
        releaseDate = "2025-10-01",
        features = listOf("Voice FX", "Collage", "Export")
    )

    fun getCurrentPack(): PackMetadata = currentPack

    fun updatePack(newPack: PackMetadata) {
        currentPack = newPack
    }
}
