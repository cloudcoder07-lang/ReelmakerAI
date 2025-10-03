package com.reelmakerai.export

import android.graphics.Bitmap
import com.reelmakerai.overlay.OverlayItem

data class ExportTask(
    val inputPath: String,                      // Path to input video file
    val outputPath: String,                     // Path to save exported video
    val lutBitmap: Bitmap,                      // LUT image for color grading
    val mood: String,                           // Mood label (for analytics or presets)
    val overlays: List<OverlayItem> = emptyList(), // Stickers, text, drawings
    val watermarkBitmap: Bitmap? = null,        // Optional watermark image
    val exportHD: Boolean = true,               // Flag for HD export (Pro feature)
    val voiceFXEnabled: Boolean = false         // Flag for voice-triggered FX
)
