package com.reelmakerai.export

/**
 * Represents a LUT-based export preset with a name and LUT image URL.
 */
data class ExportPreset(
    val name: String,
    val lutUrl: String
)
